# Programa 14
# Bryan Santos Oliveira
.text
.globl main

main:

# s0 = vetor
# t0 = contador (100 -> 0)
# t1 = i (0 -> 100)
# s1 = soma

lui $s0, 0x1001		# $s0 = 0x10010000
ori $t0, $zero, 100	# t0 = 100
ori $t1, $zero, 0	# t1 = 0
ori $s1, $zero, 0	# soma = 0

while:
sll $t2, $t1, 1		# t2 = 2i
addi $t2, $t2, 1	# t2 += 1
sw $t2, 0($s0)		# vet[i] = 2i + 1	
add $s1, $s1, $t2	# soma += 2i + 1
addi $s0, $s0, 4	# atualizando endereço de memória do vetor''
addi $t1, $t1, 1	# i++
addi $t0, $t0, -1	# contador--
bne $t0, $zero, while	# if (contador != 0) goto while

sw $s1, 0($s0)		# vet[101] = soma'

fim:

.data
vet_0: .word 0