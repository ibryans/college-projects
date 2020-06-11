# Bryan Santos Oliveira
.text
.globl main

main:

lui $t0, 0x1001		# t0 = 0x10010000

lw $s0, 0($t0)		# s0 = x
lw $s1, 4($t0)		# s1 = y
ori $s2, $zero, 1	# s2 = k = começa em 1

pow:
 beq $s1, $zero, atualiza	# se y chegou a 0, atualiza o k
 mult $s2, $s0			# k.x
 mflo $s2			# k = k.x
 addi $s1, $s1, -1		# y--
 j pow

atualiza:
 sw $s2, 8($t0)		# terceira posição = k

fim:
.data
x: .word 2
y: .word 5