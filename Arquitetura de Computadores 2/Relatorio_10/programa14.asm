# Programa 14
# Bryan Santos Oliveira
.text
.globl main

main:

ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 0x10010000

lw $s0, 0($t0)			# s0 = TEMP
ori $t1, $zero, 29		# Setando os limites da comparação
ori $t2, $zero, 51

maior_que_30:
sgt $t3, $s0, $t1		# if (temp > 29) t3 = 1
beq $t3, $zero, clear_flag	# temp < 30, vai colocar 0 em flag
	
menor_que_cinquenta:
slt $t3, $s0, $t2		# if (temp < 51) t3 = 1
beq $t3, $zero, clear_flag	# temp > 50, vai colcoar 0 em flag
	
set_flag:
sw $t3, 4($t0)			# flag = 1
j fim	

clear_flag:
sw $zero, 4($t0)		# flag = 0

fim:

.data
TEMP: .word 60
FLAG: .word -1