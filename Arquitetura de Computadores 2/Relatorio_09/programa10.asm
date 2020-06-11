.text
.globl main

main:
ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 10010000

lw $s0, 0($t0)			# s0 = x
lw $s1, 4($t0)			# s1 = z
lw $s2, 8($t0)			# s2 = y

sll $t1, $s0, 7			# t1 = x << 7 = 128x
sub $t1, $t1, $s0		# t1 = 128x - x = 127x

sll $t2, $s1, 6			# t2 = z << 6 = 64z
add $t2, $t2, $s1		# t2 = 64z + z = 65z

sub $s2, $t1, $t2		# s2 = 127x - 65z
addi $s2, $s2, 1		# s2 += 1

sw $s2, 8($t0)			# y = s2

.data
x: .word 5
z: .word 7
y: .word 0