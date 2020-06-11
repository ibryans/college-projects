# Bryan Santos Oliveira
.text
.globl main

main:

lui $t0, 0x1001		# t0 = 0x10010000

lw $s0, 0($t0)		# s0 = x
lw $s1, 4($t0)		# s1 = y

mult $s0, $s1		# x * y
mflo $s2		# k = x * y

sw $s2, 8($t0)		# terceira posição = k

fim:
.data
x: .word 5
y: .word 10