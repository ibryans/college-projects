#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TRUE  1
#define FALSE 0
#define MAX 1000

int isNotEnd(char *entry) {
    return (strlen(entry) >= 4 && (entry[0] == 'F' && entry[1] == 'I' && entry[2] == 'M'))
    ? FALSE
    : TRUE;
}

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



int r_isPalindrome(char *entry, int first, int last) {
    int result = FALSE;

    if (first < strlen(entry)-2) {
        if (entry[first] == entry[last]) {
            result = r_isPalindrome(entry, first+1, last-1);
        }
    } else {
        if (entry[first] == entry[last]) {
            result = TRUE;
        }
    }

    return result;
}

int isPalindrome(char *entry) {
    int result = r_isPalindrome(entry, 0, strlen(entry)-2);
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
