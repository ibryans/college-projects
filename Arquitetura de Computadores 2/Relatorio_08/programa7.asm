## programa 7
## Bryan Santos Oliveira
.text
.globl main
main:
ori $8, $0, 0x01   # $8 = 1
sll $8, $8, 16     # $8 = 1 << 16 (desloca o máxim)
sll $8, $8, 15     # $8 = 1 << 16 (desloca o 1 até o último bit)
sra $8, $8, 16     # $8 = shift right aritmético pra voltar o 1 e manter nos bits restantes 
sra $8, $8, 15     # $8 = acaba de deslocar e chega em 0xFFFFFFFF