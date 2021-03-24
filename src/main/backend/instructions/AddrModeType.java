package backend.instructions;

enum AddrModeType {
  REG,                          // Register
  VAL,                          // =Value
  STRING_VAL,                   // =msg_index
  IMM,                          // #value
  LOGIC_SHIFT_L,                // LSL
  LOGIC_SHIFT_R,                // LSR
  ARITH_SHIFT_L,                // ASL
  ARITH_SHIFT_R,                // ASR
  ADDR_OFFSET,                  // Register + offset
  ADDR_OFFSET_WRITEBACK         // Pre-indexing
}
