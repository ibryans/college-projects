## programa 6
## Bryan Santos Oliveira
.text
.globl main
main:
ori $8, $0, 0xffff   # x = maior número possível de 16 bits
sll $8, $8, 12       # desloca 12 bits (final dos 16 bits)
sll $8, $8, 16       # desloca o ffff até os ultimos bits
sra $8, $8, 16       # volta o deslocamento usando sr aritmetico
sra $8, $8, 12       # volta até o final pra chegar em 0xFFFFFFFF (maior num possivel)
ori $9, $0, 50000    # y = 50.000
sll $10, $9, 1       # aux = 100.000
add $9, $9, $10      # y = 50.000 + 100.000 (150.000)
sll $9, $9, 1        # y = 150.000 << 1 = 300.000
sll $11, $9, 2       # aux2 = y << 2 = 300.000 * 4
sub $12, $8, $11     # z = x - 4y ($11)