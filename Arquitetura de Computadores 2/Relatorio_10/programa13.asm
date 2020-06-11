# Programa 13
# Bryan Santos Oliveira
.text
.globl main

main:

ori $t0, $zero, 0x1001
sll $t0, $t0, 16		# $t0 = 0x10010000

lw $s0, 0($t0)			# s0 = a

srl $t1, $s0, 31		# pegando o bit de sinal usando shift right
beq $t1, $zero, fim		# se o bit de sinal for 0 (numero positivo) vai pro fim, se nao, continua

sub $s1, $zero, $s0		# s1 = a positivo (módulo)

sw $s1, 0($t0)			# Substitui o valor de a pelo seu módulo

fim:

.data
a: .word -5