package frontend.errorlistener;

import java.util.ArrayList;
import java.util.List;

public class SemanticErrorCollector {

  private static final List<String> errors = new ArrayList<>();

  public static void printErrors() {
    System.out.println("Errors detected during compilation! Exit code 200 returned.");
    for (String s : errors) {
      System.out.println("Semantic Error at " + s);
    }
    System.out.println(
        errors.size() + " semantic error(s) detected, no further compilation attempted");
  }

  public static void addIncompatibleType(
      String expected, String actual, String token, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Incompatible Types at %s, "
                + "Expected Type: %s, "
                + "but Actual Type: %s",
            line, position, token, expected, actual);
    addError(errorMsg);
  }

  public static void addUnknownType(String typeName, int line, int position) {
    String errorMsg =
        String.format("line %d:%d -- Type %s is not defined", line, position, typeName);
    addError(errorMsg);
  }

  public static void addVariableUndefined(String varName, int line, int position) {
    String errorMsg =
        String.format("line %d:%d -- Variable %s is not defined in this scope", line, position, varName);
    addError(errorMsg);
  }

  public static void addSymbolAlreadyDefined(String symbol, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- \"%s\" is already defined in this scope", line, position, symbol);
    addError(errorMsg);
  }

  public static void addFunctionUndefined(String funcName, int line, int position) {
    String errorMsg =
        String.format("line %d:%d -- Function %s is not defined", line, position, funcName);
    addError(errorMsg);
  }

  public static void addTypeMismatch(int line, int position, String op) {
    String errorMsg = String.format("line %d:%d -- Type mismatch at %s", line, position, op);
    addError(errorMsg);
  }

  public static void addFuncInconsistentArgsError(
      int line, int pos, String funcName, int paramSize, int argsSize) {
    String errorMsg =
        String.format(
            "line %d:%d -- Function %s expected %d arguments but got %d arguments",
            line, pos, funcName, paramSize, argsSize);
    addError(errorMsg);
  }

  public static void addFuncInconsistentArgTypeError(
      int line, int pos, String funcName, int index, String paramType, String argType) {
    String errorMsg =
        String.format(
            "line %d:%d -- Function %s argument %d expected type: %s but got actual type: %s",
            line, pos, funcName, index, paramType, argType);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addIsNotFuncError(int line, int pos, String funcName, String gotType) {
    String errorMsg =
        String.format(
            "line %d:%d -- %s is not a function, it is a %s", line, pos, funcName, gotType);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addGlobalReturnError(int line, int pos) {
    String errMsg =
        String.format("line %d:%d -- Cannot return from the " + "global scope", line, pos);
    addError(errMsg);
  }

  public static void addCannotBeIndexed(int line, int pos, String var, String type) {
    String errorMsg =
        String.format("line %d:%d -- %s (Actual type: %s) is not an array and cannot be indexed", line, pos, var, type);
    addError(errorMsg);
  }

  public static void addIfReturnTypesError(int line) {
    addError(line + " -- Return types of if-statement do not match up");
  }

  public static void addArrayInconsistentTypes(
      int line, int pos, int index, String exp, String actual) {
    String errorMsg =
        String.format(
            "line %d:%d -- Array doesn't have consistent types, "
                + "index %d has expected type: %s but got actual type: %s",
            line, pos, index, exp, actual);
    addError(errorMsg);
  }

  public static void addIncompatibleReturnTypes(
      String expected, String actual, String token, int line, int pos) {
    String errorMsg =
        String.format(
            "line %d:%d -- Incompatible Types at %s, "
                + "Expected Return Type: %s, "
                + "but Actual Return Type: %s",
            line, pos, token, expected, actual);
    addError(errorMsg);
  }

  public static void addConstructorHasDifferentNameAsClass(
      String className, String constructorName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Constructor in class %s does not have the same name, got %s instead",
            line, position, className, constructorName);
    addError(errorMsg);
  }

  public static void addIncompatibleTypeInConstructor(
      String expected, String actual, String construct, String token, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Incompatible Types in class constructor %s at %s, "
                + "Expected Type: %s, "
                + "but Actual Type: %s",
            line, position, construct, token, expected, actual);
    addError(errorMsg);
  }

  public static void addInconsistentNumberOfParamInConstructor(
     String param, String type, String construct, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Incompatible Types in class constructor %s, "
                + "Missing parameter %s with type %s",
            line, position, construct, param, type);
    addError(errorMsg);
  }

  public static void addFunctionHasSameNameAsClassConstructor(
      String funcName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Function %s has the same name as its class Constructor",
            line, position, funcName);
    addError(errorMsg);
  }

  public static void addClassNotDefined(
      String className, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class %s is not defined",
            line, position, className);
    addError(errorMsg);
  }

  public static void addClassConstructorInconsistentArgsError(
      int line, int position, String constructName, int expected, int actual) {
    String errorMsg =
        String.format("line %d:%d -- Constructor %s expected %d arguments but got %d arguments",
            line, position, constructName, expected, actual);
    addError(errorMsg);
  }

  public static void addIsNotConstructor(int line, int position,
      String constructName, String actual) {
    String errorMsg =
        String.format(
            "line %d:%d -- %s is not a constructor, it is a %s",
            line, position, constructName, actual);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addClassConstructorNotDefinedError(
      String className, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class constructor %s is not defined",
            line, position, className);
    addError(errorMsg);
  }

  public static void addConstructorInconsistentArgTypeError(
      int line, int pos, String constructName, int index, String paramType, String argType) {
    String errorMsg =
        String.format(
            "line %d:%d -- Constructor %s argument %d expected type: %s but got actual type: %s",
            line, pos, constructName, index, paramType, argType);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addVariableIsNotAnInstanceOfAClass(
      String varName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Variable %s is not an instance of a class",
            line, position, varName);
    addError(errorMsg);
  }

  public static void addClassDoesNotHaveAttribute(
      String className, String attributeName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class %s does not have attribute %s",
            line, position, className, attributeName);
    addError(errorMsg);
  }

  public static void addClassDoesNotHaveFunction(
      String className, String funcName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class %s does not have function %s",
            line, position, className, funcName);
    addError(errorMsg);
  }

  public static void addClassAttributeIsPrivate(
      String className, String attributeName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Attribute %s in class %s is private",
            line, position, attributeName, className);
    addError(errorMsg);
  }

  public static void addClassFunctionIsPrivate(
      String className, String funcName, int line, int position) {
    String errorMsg =
        String.format(
            "line %d:%d -- Function %s in class %s is private",
            line, position, funcName, className);
    addError(errorMsg);
  }

  public static void addIsNotClassFuncError(int line, int pos, String className, String funcName, String gotType) {
    String errorMsg =
        String.format(
            "line %d:%d -- %s.%s is not a function, it is a %s", line, pos, className, funcName, gotType);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addClassFuncInconsistentArgsError(
      int line, int pos, String className, String funcName, int paramSize, int argsSize) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class function %s.%s expected %d arguments but got %d arguments",
            line, pos, className, funcName, paramSize, argsSize);
    addError(errorMsg);
  }

  public static void addClassFuncInconsistentArgTypeError(
      int line, int pos, String className, String funcName, int index, String paramType, String argType) {
    String errorMsg =
        String.format(
            "line %d:%d -- Class function %s.%s argument %d expected type: %s but got actual type: %s",
            line, pos, className, funcName, index, paramType, argType);
    SemanticErrorCollector.addError(errorMsg);
  }

  public static void addIncompatibleWithDynamicVariables(int line, int pos) {
    String errorMsg = String.format(
        "line %d:%d -- Cannot declare/assign nested pairs or array elements with dynamic variables",
        line, pos);
    SemanticErrorCollector.addError(errorMsg);
  }

  private static void addError(String s) {
    errors.add(s);
  }

  public static void init() {
    errors.clear();
  }

  public static int getNumberOfSemanticErrors() {
    return errors.size();
  }


}
