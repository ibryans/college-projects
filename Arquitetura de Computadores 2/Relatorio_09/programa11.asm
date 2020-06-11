.text
.globl main

main:
ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 10010000

lw $s0, 0($t0)			# s0 = x
lw $s1, 4($t0)			# s1 = z
lw $s2, 8($t0)			# s2 = y

ori $t1, $zero, 25000		# temp = 25000
sll $t1, $t1, 2			# temp = 25.000 << 2 = 100.000
addi $t1, $t1, 25000		# temp = 125.000
addi $t1, $t1, 25000		# temp = 150.000
sll $t1, $t1, 1			# temp = 150.000 << 2 = 300.000 :)

sub $s2, $s0, $s1		# s2 = x - z
add $s2, $s2, $t1		# s3 = x - z + 300000

sw $s2, 8($t0)


.data
x: .word 100000
z: .word 200000
y: .word 0