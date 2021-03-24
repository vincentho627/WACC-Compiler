package frontend.symboltable;

public abstract class Identifier {

  // Public by default
  private Visibility visibility = Visibility.PUBLIC;

  public void setVisibility(Visibility visibility) {
    this.visibility = visibility;
  }

  public Visibility getVisibility() { return visibility; }

  public abstract TypeID getType();
}
