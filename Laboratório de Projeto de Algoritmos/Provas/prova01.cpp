/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * EX01 - Prova Intermediária
 * Complexidade: O(n), n sendo o tamanho da frase original falada pelo juiz. O algoritmo irá percorrer por toda a string apenas uma vez, portanto, O(n).
 */

#include <iostream>
#include <stdio.h>
using namespace std;

int main() {

    int t;
    string fraseOriginal, fraseTime1, fraseTime2;

    // Lendo t com getline para evitar o \n no buffer que atrapalha os outros getlines
    string temp;
    getline(cin, temp);
    t = stoi(temp);

    // Loop de instâncias
    for (int j = 0; j < t; j++) {
        getline(cin, fraseOriginal);
        getline(cin, fraseTime1);
        getline(cin, fraseTime2);

        int coincidenciasT1 = 0, coincidenciasT2 = 0;

        // Critério de desempate
        // 1: Em uma determinado posição, o Time 1 acertou e o Time 2 errou
        // 2: Em uma determinado posição, o Time 1 errou e o Time 2 acertou
        int desempate = -1;

        // Percorrer cada frase contando o número de coincidências
        for (int i = 0; i < fraseTime1.length() && i < fraseOriginal.length(); i++) {

            bool t1acertou = false;
            bool t2acertou = false;

            if (fraseTime1.at(i) == fraseOriginal.at(i)) {
                t1acertou = true;
                coincidenciasT1++;
            }

            if (fraseTime2.at(i) == fraseOriginal.at(i)) {
                t2acertou = true;
                coincidenciasT2++;
            }

            // Se ainda não teve o criterio de desempate
            if (desempate == -1) {

                // Se o time 1 acertou
                if (t1acertou) {
                    // E o time 2 errou
                    if (!t2acertou) {
                        desempate = 1;  // em caso de empate, time 1 leva
                    }
                } 
                
                // Se o time 1 errou
                else {
                    // E o 2 acertou
                    if (t2acertou) {
                        desempate = 2;  // em caso de empate, time 2 leva
                    }
                }
            }
        }

        cout << "Instancia " << j+1 << "\n";

        // Se for empate, verifica o criterio de desempate
        if (coincidenciasT1 == coincidenciasT2) {

            if (desempate == 1)
                cout << "time 1\n";
            else if (desempate == 2)
                cout << "time 2\n";
            else
                cout << "empate\n";

        } else if (coincidenciasT1 > coincidenciasT2) {
            cout << "time 1\n";
        } else {
            cout << "time 2\n";
        }

        cout << "\n";
    }
    

    return 0;
}