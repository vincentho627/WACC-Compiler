package backend.instructions;

public abstract class Condition {

  public static final String NO_CON = "";

  public static final String EQ = "EQ"; // Equality
  public static final String NE = "NE"; // Not equal
  public static final String LT = "LT"; // Strictly Less than
  public static final String GT = "GT"; // Strictly greater than
  public static final String GE = "GE"; // Greater than
  public static final String LE = "LE"; // Less than

  public static final String CS = "CS"; // Carry set
  public static final String VS = "VS"; // Overflow

}
