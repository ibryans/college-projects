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

void r_randomChange(char *entry, char first, char second, int index) {
    if (index < strlen(entry)-1) {
        if (entry[index] == first) 
            entry[index] = second;
        r_randomChange(entry, first, second, index+1);
    }
}

void randomChange(char *entry, char first, char second) {
    r_randomChange(entry, first, second, 0);
    printf("%s", entry);
}

int main() {

    srand(4);

    char firstRandomLetter;
    char secondRandomLetter;

    char entry[MAX];

    do {
        
        fgets(entry, MAX, stdin);

        if (isNotEnd(entry)) {
            firstRandomLetter  = (char) 97 + rand() % (26);
            secondRandomLetter = (char) 97 + rand() % (26);
            randomChange(entry, firstRandomLetter, secondRandomLetter);
        }

    } while (isNotEnd(entry));

}
