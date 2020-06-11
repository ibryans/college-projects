.text
.globl main
main:
li $t0,53

sll $t1,$t0,2
srl $t2,$t0,2
sra $t3,$t0,2

print $t1
print $t2
print $t3