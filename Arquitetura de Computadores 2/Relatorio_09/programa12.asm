.text
.globl main

main:

ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 10010000
addi $t0, $t0, 12		# $t0 = 1001000c (p3)

lw $s1, 0($t0)			# s1 = p3
lw $s1, 0($s1) 			# s1 = p2
lw $s1, 0($s1)			# s1 = p1
lw $s2, 0($s1)			# s2 = x

sll $s2, $s2, 1			# s2 = x*2

sw $s2, 0($s1)			# x = $s2

.data

# O inteiro em si
k: .word 1

# Criando os ponteiros (o valor de cada um é o endereço do anterior)
p1: .word 0x10010000
p2: .word 0x10010004
p3: .word 0x10010008