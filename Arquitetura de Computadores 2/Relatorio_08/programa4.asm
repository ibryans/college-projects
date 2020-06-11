## programa 4
## Bryan Santos Oliveira
.text
.globl main
main:
addi $8, $0, 3     # x = 3
addi $9, $0, 4     # y = 4
sll  $10, $8, 4    # aux = x << 4 = 16x
sub  $8, $10, $8   # x = aux - x (15x)
sll  $11, $9, 6    # aux2 = y << 6 = 64y
sll  $12, $9, 1    # aux3 = y << 1 = 2y
add  $11, $11, $12 # aux2 = aux2 + aux3 (64y + 2y = 66y)
add  $9, $11, $9   # y = 66y + y (67y)
add  $13, $8, $9   # z = 15x + 67y
sll  $13, $13, 2   # z = z << 2 = z * 4
