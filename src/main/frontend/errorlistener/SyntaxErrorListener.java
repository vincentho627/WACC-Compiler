package frontend.errorlistener;

import antlr.WaccParser.Begin_statContext;
import antlr.WaccParser.Exit_statContext;
import antlr.WaccParser.If_statContext;
import antlr.WaccParser.Return_statContext;
import antlr.WaccParser.Sequence_statContext;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class SyntaxErrorListener extends BaseErrorListener {

  private final List<String> errorMessages = new ArrayList<>();
  private boolean intOverflowDetected = false;
  private boolean returnErrorDetected = false;

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
      int line, int charPositionInLine, String msg, RecognitionException e) {
    errorMessages.add(line + ":" + charPositionInLine + " -- " + msg);
  }

  //---FUNCTIONS TO TEST FOR INT UNDER/OVERFLOW---

  public void intError(int line, boolean over) {
    this.intOverflowDetected = true;
    String status;
    if (over) {
      status = "overflow";
    } else {
      status = "underflow";
    }
    errorMessages.add(line + " -- Integer " + status + " detected.");
  }

  public boolean hasIntError() {
    return this.intOverflowDetected;
  }

  //---END OF FUNCTIONS TO TEST FOR INT UNDER/OVERFLOW---

  //---FUNCTIONS TO TEST FOR MISSING RETURNS---

  public void returnCheck(ParserRuleContext ctx) {
    //Stat position is 1 less than END terminal
    int stat_position = ctx.children.size() - 2;
    if (!correctReturn(ctx.children.get(stat_position))) {
      returnErrorDetected = true;
      errorMessages.add(ctx.start.getLine() + " -- Function has no return.");
    }
  }

  private static final int SEQ_STAT_RETURN_POSITION = 2;
  private static final int IF_STAT_RETURN_POSITION = 3;
  private static final int ELSE_STAT_RETURN_POSITION = 5;
  private static final int BEGIN_STAT_RETURN_POSITION = 2;

  private boolean correctReturn(ParseTree ctx) {
    //base case
    if (ctx instanceof Return_statContext || ctx instanceof Exit_statContext) {
      return true;

      //Sequential composition, always pick final stat
    } else if (ctx instanceof Sequence_statContext) {
      return correctReturn(ctx.getChild(SEQ_STAT_RETURN_POSITION));

      //Need to look at both branch of if statements
    } else if (ctx instanceof If_statContext) {
      return correctReturn(ctx.getChild(IF_STAT_RETURN_POSITION)) &&
          correctReturn(ctx.getChild(ELSE_STAT_RETURN_POSITION));

      //Check if there's a return in begin ... end
    } else if (ctx instanceof Begin_statContext) {
      return correctReturn(ctx.getChild(BEGIN_STAT_RETURN_POSITION));

    } else {
      return false;
    }
  }

  public boolean hasReturnError() {
    return this.returnErrorDetected;
  }

  //---END OF FUNCTIONS TO TEST FOR MISSING RETURNS---

  public void printErrors() {
    System.out
        .println("Errors during compilation! Exit code 100 returned.");
    for (String s : errorMessages) {
      System.out.println("Syntactic error at line " + s);
    }
    System.out.println(errorMessages.size()
        + " syntax error(s) detected, no further compilation attempted");
  }
}
