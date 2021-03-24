package frontend.symboltable;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

  private final Map<String, Identifier> dictionary = new HashMap<>();
  private int size = 0;
  private int tempFuncOffset = 0;
  private boolean funcContext = false;
  private boolean classContext = false;
  private boolean skipLR = false;
  private final Map<String, Integer> varOffsets = new LinkedHashMap<>();
  private final SymbolTable parent;
  private String className = "";

  public int getSize() {
    return size;
  }

  public SymbolTable(SymbolTable parent) {
    this.parent = parent;
  }

  /** Defines a top-level symbol table, pre-loaded with default types. */
  public SymbolTable() {
    this(null);
    loadTopLevelSymbolTable(this);
  }

  /** Loads a top-level symbol table for the global scope. */
  private void loadTopLevelSymbolTable(SymbolTable symbolTable) {
    symbolTable.add(new IntID());
    symbolTable.add(new BoolID());
    symbolTable.add(new CharID());
    symbolTable.add(new StringID());
    symbolTable.add(new NullID());
  }

  /** Checks if this symbol table is a top-level one, i.e. of global scope. */
  public boolean isTopLevel() {
    return parent == null;
  }

  // For types only
  public void add(TypeID type) {
    dictionary.put(type.getTypeName(), type);
  }

  public void add(String name, Identifier identifier) {
    dictionary.put(name, identifier);
  }

  /** Finds identifiers within this symbol table only. */
  public Identifier lookup(String name) {
    return dictionary.get(name);
  }

  /** Finds identifiers in this symbol table and any of its parent symbol tables. */
  public Identifier lookupAll(String name) {
    SymbolTable temp = this;
    Identifier node;
    while (temp != null) {
      node = temp.lookup(name);
      if (node != null) {
        if (node instanceof ParamID) {
          return node.getType();
        }
        return node;
      }
      temp = temp.parent;
    }
    return null;
  }

  public Set<String> getAllIdent() {
    return dictionary.keySet();
  }

  public String getClassName() { return className; }

  public void setClassName(String className) { this.className = className; }

  public void replaceType(String name, TypeID type) {
    SymbolTable temp = this;
    Identifier node;
    while (temp != null) {
      node = temp.lookup(name);
      if (node != null) {
        temp.add(name, type);
        return;
      }
      temp = temp.parent;
    }
  }

  public void incrementSize(int val) {
    size += val;
  }

  public void incrementFuncOffset(int val) {
    tempFuncOffset += val;
  }

  public void resetFuncOffset() {
    tempFuncOffset = 0;
  }

  public void setFuncContext() {
    funcContext = true;
  }

  public void setClassContext() {
    classContext = true;
  }

  public void setSkipLR() {
    skipLR = true;
  }

  public boolean getFuncContext() {
    return funcContext;
  }

  public boolean getClassContext() {
    return classContext;
  }

  public int getStackOffset(String var) {
    SymbolTable temp = this;
    int innerOffset = 0;
    Identifier typeID = lookupAll(var);
    while (!temp.varOffsets.containsKey(var)) {
      if (!(typeID instanceof ClassAttributeID)) {
        innerOffset += temp.size;
      }
      temp = temp.parent;
    }

    return innerOffset + tempFuncOffset + temp.varOffsets.get(var);
  }

  public void addOffset(String var, int offset) {
    varOffsets.put(var, offset);
  }

  public int getSmallestOffset() {
    if (varOffsets.isEmpty()) {
      return size;
    }
    int ans = Collections.min(varOffsets.values());
    if (skipLR) {
      skipLR = false;
      return ans - 4;
    } else {
      return ans;
    }
  }

  public SymbolTable getParent() {
    return parent;
  }
}
