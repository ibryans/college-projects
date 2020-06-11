## Programa 2
.text
.globl main
main:
addi $8,$0,0x1;  # x = 1
sll $9,$8,2;     # y = x << 2 = x*4 = 4
add $9,$9,$8;    # y = y+x = 4+1 = 5
addi $9,$9,0xF;  # y = y + 15 = 5+15 = 20
