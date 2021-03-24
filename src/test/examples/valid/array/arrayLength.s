.data

msg_0:
	.word 4
	.ascii "%d\0"
msg_1:
	.word 2
	.ascii "\0"

.text

.global main
main:
	PUSH {lr}
	SUB sp, sp, #4
	LDR r4, =43
	LDR r4, =2
	LDR r4, =18
	LDR r4, =1
	LDR r0, ==20
	BL malloc
	MOV r4, r0
	STR r5, [r4, #4]
	STR r5, [r4, #8]
	STR r5, [r4, #12]
	STR r5, [r4, #16]
	LDR r5, ==4
	STR r5, [r4]
	STR r4, [sp]
	LDR r4, [sp]
	LDR r4, [r4]
	MOV r0, r4
	BL p_print_int
	BL p_print_ln
	ADD sp, sp, #4
	LDR r0, =0
	POP {pc}
	.ltorg
p_print_ln:
	PUSH {lr}
	LDR r0, =msg_1
	ADD r0, r0, #4
	BL puts
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_int:
	PUSH {lr}
	MOV r1, r0
	LDR r0, =msg_0
	ADD r0, r0, #4
	BL printf
	MOV r0, #0
	BL fflush
	POP {pc}
