## programa 5
## Bryan Santos Oliveira
.text
.globl main
main:
ori $8, $0, 50000  # x = 50.000
sll $8, $8, 1      # x = 50.000 << 1 (100.000)
sll $9, $8, 1       # y = 100.000 << 1 = 200.000
add $10, $8, $9   # z = x + y 