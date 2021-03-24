parser grammar WaccParser;

@header {
  import frontend.errorlistener.SyntaxErrorListener;
}

@parser::members {
  SyntaxErrorListener syntaxErr;

  public WaccParser(TokenStream input, SyntaxErrorListener syntaxErr) {
    this(input);
    this.syntaxErr = syntaxErr;
  }

  public void checkOverflowError(boolean isMinus, long val) {
    if (isMinus && -val < Integer.MIN_VALUE) {
      syntaxErr.intError(this._ctx.start.getLine(), false);
    } else if (!isMinus && val > Integer.MAX_VALUE) {
      syntaxErr.intError(this._ctx.start.getLine(), true);
    }
  }
}

options {
  tokenVocab=WaccLexer;
}

program: BEGIN (classes)* (func)* stat END EOF ;

// Classes
classes: CLASS IDENT IS attributeList? (classFunc)* END ;

// Class Attribute
attribute: VISIBILITY type IDENT;

// Class Attributes
attributeList: attribute (SEMI_COLON attribute)* ;


// Class Functions
classFunc: VISIBILITY func ;

// Class Instantiate
classInstant: NEW IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES ;

// Class Function Calls
callClassFunc: CALL IDENT FULL_STOP IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES ;

// Get Class Attributes
classAttr: IDENT FULL_STOP IDENT ;

// Functions
func: (type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END
{syntaxErr.returnCheck(this._ctx);}) ;

// Parameters list
paramList: param (COMMA param)* ;

// Parameters
param: type IDENT ;

// Statements
stat: SKIP_LITER                                            #skip_stat
| (type | VAR) IDENT ASSIGN assignRHS                       #var_decl_stat
| IDENT IDENT ASSIGN assignRHS                              #class_var_decl_stat
| assignLHS ASSIGN assignRHS                                #assign_stat
| READ assignLHS                                            #read_stat
| FREE expr                                                 #free_stat
| RETURN expr                                               #return_stat
| EXIT expr                                                 #exit_stat
| PRINT expr                                                #print_stat
| PRINTLN expr                                              #println_stat
| IF expr THEN stat ELSE stat FI                            #if_stat
| WHILE expr DO stat DONE                                   #while_stat
| DO stat WHILE expr DONE                                   #do_while_stat
| FOR IDENT IN expr UNTIL expr DO stat DONE                 #for_stat
| stat SEMI_COLON stat                                      #sequence_stat
| BEGIN stat END                                            #begin_stat
;

// Assignments
assignLHS: IDENT                                            #ident_assignLHS
| arrayElem                                                 #arrayElem_assignLHS
| pairElem                                                  #pairElem_assignLHS
| classAttr                                                 #classattr_assignLHS
;

assignRHS: expr                                                     #expr_assignRHS
| arrayLiter                                                        #arrayLiter_assignRHS
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES        #newPair_assignRHS
| pairElem                                                          #pairElem_assignRHS
| CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES            #call_assignRHS
| callClassFunc                                                     #callClassFunc_assignRHS
| classInstant                                                      #instantiate_assignRHS
;

argList: expr (COMMA expr)* ;

pairElem: FST expr | SND expr ;

// Types
type: baseType
| arrayType
| pairType
;

baseType: INT
| BOOL
| CHAR
| STRING
;

arrayType: (baseType | pairType) (OPEN_SQUARE_BRACKETS CLOSE_SQUARE_BRACKETS)+ ;

pairType: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElemType: baseType
| arrayType
| PAIR
;

expr:
(PLUS | MINUS)?
(INTEGER {
    boolean isMinus = ((IntLiterContext) this._ctx).MINUS() != null;
    checkOverflowError(isMinus, Long.valueOf($INTEGER.text));
  })                                                                  #intLiter
| (PLUS | MINUS)? (BINARY_INTEGER {
    String token = $BINARY_INTEGER.text.substring(2);
    boolean isMinus = ((BinIntLiterContext) this._ctx).MINUS() != null;
    long val = Long.parseLong(token, 2);
    checkOverflowError(isMinus, val);
  })                                                                  #binIntLiter
| (PLUS | MINUS)? (OCTAL_INTEGER {
    String token = $OCTAL_INTEGER.text.substring(2);
    boolean isMinus = ((OctIntLiterContext) this._ctx).MINUS() != null;
    long val = Long.parseLong(token, 8);
    checkOverflowError(isMinus, val);
  })                                                                  #octIntLiter
| (PLUS | MINUS)? (HEXADECIMAL_INTEGER {
    String token = $HEXADECIMAL_INTEGER.text.substring(2);
    boolean isMinus = ((HexIntLiterContext) this._ctx).MINUS() != null;
    long val = Long.parseLong(token, 16);
    checkOverflowError(isMinus, val);
  })                                                                  #hexIntLiter
| (TRUE | FALSE)                                                      #boolLiter
| CHAR_LITER                                                          #charLiter
| STR_LITER                                                           #strLiter
| NULL                                                                #pairLiter
| IDENT                                                               #identExpr
| arrayElem                                                           #arrElemExpr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES                             #paranExpr
| unaryOper expr                                                      #unOpExpr
| expr BIT_AND expr                                                   #bitAndExpr
| expr BIT_OR expr                                                    #bitOrExpr
| expr arithmeticOper1 expr                                           #arithOpExpr_1
| expr arithmeticOper2 expr                                           #arithOpExpr_2
| expr binaryOper1 expr                                               #binOpExpr_1
| expr binaryOper2 expr                                               #binOpExpr_2
| expr AND expr                                                       #andExpr
| expr OR expr                                                        #orExpr
| classAttr                                                           #classAttrExpr
;

unaryOper: NOT | MINUS | LEN | ORD | CHR | BIT_NOT ;
arithmeticOper1: MULT | DIV | MOD ;
arithmeticOper2: PLUS | MINUS ;
binaryOper1: GT | GTE | LT | LTE ;
binaryOper2: EQ | NE ;

arrayElem: IDENT (OPEN_SQUARE_BRACKETS expr CLOSE_SQUARE_BRACKETS)+ ;

arrayLiter: OPEN_SQUARE_BRACKETS (expr (COMMA expr)*)? CLOSE_SQUARE_BRACKETS ;
