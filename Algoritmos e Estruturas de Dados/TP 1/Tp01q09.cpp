#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TRUE  1
#define FALSE 0
#define MAX 1000

/*
* Bryan Santos - 02/2020
*/

void r_printValues(FILE *arq, int index, int n) {
    char value[50];
    fgets(value, 50, arq);

    if (index < n) {
        r_printValues(arq, index+1, n);    
    }

    printf("%s", value);
}

void printValues(int n) {
    FILE *arq = fopen("tp_08_values_cpp.txt", "r");
    r_printValues(arq, 0, n);
    fclose(arq);
}


void readAndSaveValues(int n) {
    FILE *arq = fopen("tp_08_values_cpp.txt", "w");
    float entry;

    if (arq)

    for (int i = 0; i < n; i++) {
        scanf("%f", &entry);
        fprintf(arq, "%g\n", entry);
    }

    fclose(arq); 
}

int main() {
    int n;

    scanf("%d", &n);

    readAndSaveValues(n);
    printValues(n);

    return 0;
}