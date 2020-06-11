.text
.globl main

main:
ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 10010000

lw $s0, 0($t0)			# s0 = x1
lw $s1, 4($t0)			# s1 = x2
lw $s2, 8($t0)			# s2 = x3
lw $s3, 12($t0)			# s3 = x4
lw $s4, 16($t0)			# s4 = soma

add $s4, $s0, $s1		# s4 = x1 + x2
add $t1, $s2, $s3		# temp = x3 + x4
add $s4, $s4, $t1		# s4 = s4 + temp (x1 + x2 + x3 + x4)

sw $s4, 16($t0)			# soma = s4

.data
x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17
soma: .word -1