## Programa 3
.text
.globl main
main:
addi $8,$0,0x3;  # x = 3
addi $9,$0,0x4;  # y = 4
sll $10,$8,4;    # tmp = x << 4 = 16x
sub $10,$10,$8;  # tmp = 16x - x (15x)
sll $11,$9,6;    # tmp2 = y << 6 = 64y
sll $12,$9,1;    # tmp3 = y << 1 = 2y
add $11,$11,$12; # tmp2 += tmp3 (64y + 2y = 66y)
add $11,$11,$9;  # tmp2 += y (66y + y = 67y)
add $15,$10,$11; # z = 15x + 67y
sll $15,$15,2;   # z = z << 2 = z * 4
