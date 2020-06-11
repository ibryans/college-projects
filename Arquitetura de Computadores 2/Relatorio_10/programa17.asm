# Bryan Santos Oliveira
.text
.globl main

main:

lui $t0, 0x1001		# t0 = 0x10010000

lw $s0, 0($t0)		# s0 = x

sll $t1, $s0, 31	# t1 vai receber o bit mais à direita de x como bit mais significativo
srl $t1, $t1, 31	# t1 vai ser 0 ou 1

beq $t1, $zero, par	# if (t1 == 0) é par

impar:
mult $s0, $s0	# fazer com que o s3 = x³
mflo $s3	# s3 = x²
mult $s3, $s0	# x². x = x³
mflo $s3	# s3 = x³

mult $s0, $s0	# fazer com que o s5 = x⁵
mflo $s5	# s3 = x²
mult $s5, $s0	# x². x = x³
mflo $s5	# s3 = x³
mult $s5, $s0	# x³. x = x⁴
mflo $s5	# s3 = x⁴
mult $s5, $s0	# x⁴. x = x⁵
mflo $s5	# s3 = x⁵

sub $s1, $s5, $s3	# s1 = x⁵ - x³
addi $s1, $s1, 1	# x⁵ - x3 + 1

sw $s1, 4($t0)		# y = x⁵ - x³ + 1
j fim

par:
mult $s0, $s0		# fazer com que o s2 = 2x²
mflo $s2		# s2 = x²
sll $s2, $s2, 1		# s2 = 2x²

mult $s0, $s0		# fazer com que o s3 = x³
mflo $s3		# s3 = x²
mult $s3, $s0		# x². x = x³
mflo $s3		# s3 = x³

mult $s0, $s0		# fazer com que o s4 = x⁴
mflo $s4		# s4 = x²
mult $s4, $s0		# x². x = x³
mflo $s4		# s4 = x³
mult $s4, $s0		# x³. x = x⁴
mflo $s4		# s4 = x⁴

add $s1, $s4, $s3	# s1 = x⁴ + x³
sub $s1, $s1, $s2	# s1 = x⁴ + x³ - 2x²

sw $s1, 4($t0)		# y = x⁴ + x³ - 2x²

fim:

.data
x: .word 3
y: .word -1