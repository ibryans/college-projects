#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TRUE  1
#define FALSE 0
#define MAX 1000

void removeSeparators(char *entry) {
    int count = 0;
    for (int i = 0; i < strlen(entry)-2; i++) {
        if (entry[i] != ' ' || 
                entry[i] != ',' || 
                entry[i] != '-' || 
                entry[i] != '.' || 
                entry[i] != '/' || 
                entry[i] != '(' || 
                entry[i] != ')' || 
                entry[i] != '"' || 
                entry[i] != ':')
            
            entry[count++] = entry[i];
    }
}

int isNotEnd(char *entry) {
    return (strlen(entry) >= 4 && (entry[0] == 'F' && entry[1] == 'I' && entry[2] == 'M'))
    ? FALSE
    : TRUE;
}

int isPalindrome(char *entry) {

    int result = TRUE;

    for (int i = 0, j = strlen(entry)-2; i < strlen(entry)-2; i++, j--) {
        if (entry[i] != entry[j])
            result = FALSE;
    }
    
    return result;
}

int main(int argc, char const *argv[]) {
    
    char entry[MAX];

    do {

        fgets(entry, MAX, stdin);

        if (isNotEnd(entry) == TRUE) {
            removeSeparators(entry);
            switch (isPalindrome(entry)) {
                case TRUE:
                    printf("SIM\n");
                    break;
                case FALSE:
                    printf("NAO\n");
            }
        }

    } while (isNotEnd(entry) == TRUE);
    
    return 0;
}
