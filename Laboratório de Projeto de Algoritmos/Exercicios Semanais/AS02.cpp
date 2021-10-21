/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS02 - Strings
 */

#include <iostream>
#include <stdio.h>
using namespace std;

/**
 * Função que calcula a quantidade de leds necessários em um painel para mostrar um determinado número
 * @param char numero
 * @return quantidade de leds
 */
int calculaLeds(char numero) {
    int leds = 0;
    switch(numero) {
        case '0': leds = 6; break;
        case '1': leds = 2; break;
        case '2': leds = 5; break;
        case '3': leds = 5; break;
        case '4': leds = 4; break;
        case '5': leds = 5; break;
        case '6': leds = 6; break;
        case '7': leds = 3; break;
        case '8': leds = 7; break;
        case '9': leds = 6; break; 
    }
    return leds;
}

int main() {

    int n;
    string entrada;

    cin >> n;

    for(int i = 0; i < n; i++) {
        cin >> entrada;
        int numLeds = 0;

        /** Para cada caracter da string, calcula o número de leds necessários e soma à <numLeds> **/
        for (int j = 0; j < entrada.length(); j++)
            numLeds += calculaLeds(entrada.at(j));

        cout << numLeds << " leds\n";
    }

    return 0;
}