package backend.instructions;

public class AddrMode extends Instr {

  private final Object operand;
  private final int offsetFromOperand;
  private final AddrModeType type;

  /**
   * Converts registers, values and offsets into a general addressing mode
   * format for any assembly language, e.g. [operand, #offsetFromOperand].
   */
  private AddrMode(Object operand, int offsetFromOperand, AddrModeType type) {
    this.operand = operand;
    this.offsetFromOperand = offsetFromOperand;
    this.type = type;
  }

  // Implemented with factory method pattern

  public static AddrMode buildReg(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.REG);
  }

  public static AddrMode buildVal(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.VAL);
  }

  public static AddrMode buildStringVal(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.STRING_VAL);
  }

  public static AddrMode buildImm(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.IMM);
  }

  public static AddrMode buildImmWithLSL(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.LOGIC_SHIFT_L);
  }

  public static AddrMode buildImmWithLSR(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.LOGIC_SHIFT_R);
  }

  public static AddrMode buildImmWithASL(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.ARITH_SHIFT_L);
  }

  public static AddrMode buildImmWithASR(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.ARITH_SHIFT_R);
  }

  public static AddrMode buildAddr(Object operand) {
    return new AddrMode(operand, 0, AddrModeType.ADDR_OFFSET);
  }

  public static AddrMode buildAddrWithOffset(Object operand,
      int offsetFromOperand) {
    return new AddrMode(operand, offsetFromOperand, AddrModeType.ADDR_OFFSET);
  }

  public static AddrMode buildAddrWithWriteBack(Object operand,
      int offsetFromOperand) {
    return new AddrMode(operand, offsetFromOperand,
        AddrModeType.ADDR_OFFSET_WRITEBACK);
  }

  @Override
  public String translateToArm() {
    switch (type) {
      case REG:
        return operand.toString();
      case VAL:
        return "=" + operand;
      case IMM:
        return "#" + operand;
      case STRING_VAL:
        return "=msg_" + operand;
      case LOGIC_SHIFT_L:
        return "LSL #" + operand;
      case LOGIC_SHIFT_R:
        return "LSR #" + operand;
      case ARITH_SHIFT_L:
        return "ASL #" + operand;
      case ARITH_SHIFT_R:
        return "ASR #" + operand;
      case ADDR_OFFSET:
        if (offsetFromOperand == 0) {
          return ("[" + operand + "]");
        } else {
          return ("[" + operand + ", #" + offsetFromOperand + "]");
        }
      case ADDR_OFFSET_WRITEBACK:
        return ("[" + operand + ", #" + offsetFromOperand + "]!");
      default:
        throw new IllegalArgumentException("Addressing mode does not exist");
    }
  }
}
