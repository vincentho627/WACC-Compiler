package frontend.abstractsyntaxtree;

import frontend.abstractsyntaxtree.expressions.IdentExprAST;
import frontend.abstractsyntaxtree.statements.BeginStatAST;
import frontend.abstractsyntaxtree.statements.ExitAST;
import frontend.abstractsyntaxtree.statements.IfAST;
import frontend.abstractsyntaxtree.statements.ReturnAST;
import frontend.abstractsyntaxtree.statements.SequenceAST;
import frontend.abstractsyntaxtree.statements.WhileAST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.symboltable.ArrayID;
import frontend.symboltable.BoolID;
import frontend.symboltable.CharID;
import frontend.symboltable.EmptyID;
import frontend.symboltable.ExitID;
import frontend.symboltable.IntID;
import frontend.symboltable.NullID;
import frontend.symboltable.PairID;
import frontend.symboltable.OptionalPairID;
import frontend.symboltable.StringID;
import frontend.symboltable.TypeID;
import frontend.symboltable.VarID;
import java.util.List;

public class Utils {

  // Used to define what types certain binary operators are supposed to support
  public static final int BOOL = 1;
  public static final int INT_CHAR = 2;
  public static final int ALL_TYPES = 3;

  public static final int FST_TYPE_NUM = 8;
  public static final int SND_TYPE_NUM = 9;

  /**
   * Checks if types t1 and t2 are the same or are compatible to each other in
   * the case of assignment.
   */
  public static boolean typeCompat(TypeID t1, TypeID t2) {
    assert (t1 != null);
    assert (t2 != null);

    // Dynamic variables
    if (t1.getType() instanceof VarID || t2.getType() instanceof VarID) {
      return true;
    }

    // For function return type comparison
    if (t1 instanceof NullID) {
      return t2 instanceof OptionalPairID;
    }

    // Check pair types
    if (t1 instanceof PairID) {
      if (t2 instanceof OptionalPairID) {
        return comparePairTypes((PairID) t1, (OptionalPairID) t2);
      }
      return false;
    }

    // Check array types
    if (t1 instanceof ArrayID) {
      if (t2 instanceof ArrayID) {
        return compareArrayTypes(t1, t2);
      }
      return false;
    }

    // Check base types
    return t1.getType() == t2.getType();
  }

  /**
   * Checks if two pair types are the same or are compatible to each other in
   * the case of assignment. Assumes pairs can be polymorphic, i.e. does not try
   * to determine the exact type of a pair if a generic pair is reached.
   */
  public static boolean comparePairTypes(PairID eLType, OptionalPairID eRType) {
    // Assigning null to pair
    if (eRType instanceof NullID) {
      return true;
    }

    // Types of LHS pair
    TypeID fstLType = eLType.getFstType();
    TypeID sndLType = eLType.getSndType();

    // Types of RHS pair
    TypeID fstRType = ((PairID) eRType).getFstType();
    TypeID sndRType = ((PairID) eRType).getSndType();

    // Do shallow comparison between types within pairs

    // Both pairs does not contain nulls
    if (!(fstLType instanceof NullID)
        && !(sndLType instanceof NullID)
        && !(fstRType instanceof NullID)
        && !(sndRType instanceof NullID)) {
      return (typeCompat(fstLType, fstRType))
          && (typeCompat(sndLType, sndRType));
    }

    // First of each pair is not null
    if (!(fstLType instanceof NullID) && !(fstRType instanceof NullID)) {
      return (typeCompat(fstLType, fstRType));
    }

    // Second of each pair is not null
    if (!(sndRType instanceof NullID) && !(sndLType instanceof NullID)) {
      return (typeCompat(sndLType, sndRType));
    }

    // Case where the first and second type of eRType is null
    return true;
  }

  /**
   * Checks if two array types are the same or are compatible to each other in
   * the case of assignment.
   */
  public static boolean compareArrayTypes(TypeID eLType, TypeID eRType) {
    // Can always assign empty to any array
    if (eRType instanceof EmptyID) {
      return true;
    }

    // Recurse to find underlying type
    if (eLType instanceof ArrayID && eRType instanceof ArrayID) {
      return compareArrayTypes(((ArrayID) eLType).getElemType(),
          ((ArrayID) eRType).getElemType());
    }

    // Call comparePairTypes is underlying type is pair
    if (eLType instanceof PairID && eRType instanceof OptionalPairID) {
      return comparePairTypes((PairID) eLType, (OptionalPairID) eRType);
    }

    // Check base types
    return typeCompat(eLType, eRType);
  }

  public static TypeID inferFinalReturnType(Node statements, int line) {
    if (statements instanceof ReturnAST) {
      // Base case
      return ((ReturnAST) statements).getExpr().getIdentifier().getType();
    }

    if (statements instanceof ExitAST) {
      // Base case
      return statements.getIdentifier().getType();
    }

    if (statements instanceof SequenceAST) {
      // Assume the final type of a sequence is found in the last statement
      List<Node> statsList = ((SequenceAST) statements).getStatements();
      return inferFinalReturnType(statsList.get(1), line);
    }

    if (statements instanceof IfAST) {
      // Need to check both branch of execution for IfAST
      Node thenStat = ((IfAST) statements).getThenStat();
      Node elseStat = ((IfAST) statements).getElseStat();
      TypeID thenID = inferFinalReturnType(thenStat, line);
      TypeID elseID = inferFinalReturnType(elseStat, line);
      if (!(thenID instanceof ExitID || elseID instanceof ExitID || typeCompat(
          thenID, elseID))) {
        // Type of an if-statement should be same regardless which statements
        SemanticErrorCollector.addIfReturnTypesError(line);
      }
      return thenID instanceof ExitID ? elseID : thenID;
    }

    if (statements instanceof WhileAST) {
      // Assume the final type of a while-block is found within the block
      return inferFinalReturnType(((WhileAST) statements).getStat(), line);
    }

    if (statements instanceof BeginStatAST) {
      return inferFinalReturnType(((BeginStatAST) statements).getStat(), line);
    }

    // UNREACHABLE (Parser makes sure that there is always return/exit)
    return null;
  }

  // Assigns number depending on type (For dynamic variables)
  public static int getTypeNumber(List<TypeID> ts) {
    int typeNumber = 0;
    for (TypeID t : ts) {
      typeNumber += getTypeNumber(t);
    }
    return typeNumber;
  }

  public static int getTypeNumber(TypeID type) {
    int typeNumber = 0;

    if (type instanceof IntID) {
      return 1;
    } else if (type instanceof BoolID) {
      return 1 << 1;
    } else if (type instanceof CharID) {
      return 1 << 2;
    } else if (type instanceof StringID) {
      return 1 << 3;
    } else if (type instanceof NullID) {
      return 1 << 4;
    } else if (type instanceof PairID) {
      return 1 << 5;
    } else if (type instanceof ArrayID) {
      return 1 << 6;
    }
    // SHOULD NOT REACH THIS POINT
    assert (false);
    return typeNumber;
  }

  public static int getSizeFromTypeNumber(int typeNumber) {
    // bool or char
    if (typeNumber == 2 || typeNumber == 4) {
      return 1;
    }
    return 4;
  }

  public static int getSizeFromTypeNumber(TypeID type) {
    int typeNumber = getTypeNumber(type);
    // bool or char
    if (typeNumber == 2 || typeNumber == 4) {
      return 1;
    }
    return 4;
  }
}
