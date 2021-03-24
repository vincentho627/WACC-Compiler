package frontend;

import antlr.WaccLexer;
import antlr.WaccParser;
import frontend.abstractsyntaxtree.AST;
import frontend.errorlistener.SemanticErrorCollector;
import frontend.errorlistener.SyntaxErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Encompasses the lexer, and the syntax and semantic checkers of the compiler.
 */
public class FrontEndAnalyser {

  private final WaccParser parser;
  private final SyntaxErrorListener syntaxErrorListener = new SyntaxErrorListener();

  private AST ast;

  public FrontEndAnalyser(CharStream stream) {
    // Create a lexer that reads from the input stream
    WaccLexer lexer = new WaccLexer(stream);
    // Remove standard error listeners from lexer
    lexer.removeErrorListeners();
    lexer.addErrorListener(syntaxErrorListener);

    // Create a buffer of tokens read from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // Create a parser that reads from the tokens buffer.
    // SyntaxErrorListener passed in to check potential int overflow and missing returns
    parser = new WaccParser(tokens, syntaxErrorListener);
    // Remove standard error listener from parser
    parser.removeErrorListeners();
    parser.addErrorListener(syntaxErrorListener);
  }

  public int run() {
    System.out.println("--- Compiling... ---");

    // Begin parsing at rule for program
    ParseTree tree = parser.program();

    // Syntax error check
    /* Second and third case needed because we treat integer overflow and
    return errors separately */
    if (parser.getNumberOfSyntaxErrors() > 0 ||
        syntaxErrorListener.hasIntError() ||
        syntaxErrorListener.hasReturnError()) {
      syntaxErrorListener.printErrors();
      return 100;
    }

    // Semantic analysis
    SemanticErrorCollector.init();
    TreeVisitor treeVisitor = new TreeVisitor();
    ast = (AST) treeVisitor.visit(tree);

    // Semantic error check
    if (SemanticErrorCollector.getNumberOfSemanticErrors() > 0) {
      SemanticErrorCollector.printErrors();
      return 200;
    }

    System.out.println("--- Parsing finished... ---");

    return 0;
  }

  public AST getAst() {
    return ast;
  }
}
