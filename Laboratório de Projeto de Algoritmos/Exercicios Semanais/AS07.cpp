/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS07 - Força Bruta
 */

#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

#define MAX_VALUE 40000
#define DISTANCIA_MINIMA 10000

/** 
 * Calcula a distância entre dois pontos através da fórmula: 
 * d = √((x_2 - x_1)² + (y_2 - y_1)²) 
 */
double calcDistancia(int x1, int y1, int x2, int y2) {
    float result = pow(x2-x1, 2) + pow(y2-y1, 2);
    result = sqrt(result);
    return result;
}


void casoDeTeste(int N) {

    int X[N], Y[N];
    double menor = MAX_VALUE;


    // Salvando as coordenadas
    for (int nC = 0; nC < N; nC++) {
        cin >> X[nC];
        cin >> Y[nC];
    }

    // Comparando a distância entre cada ponto e salvando a menor
    for (int i = 0; i < N; i++) {
        for (int j = i+1; j < N; j++) {
            double dist = calcDistancia(X[i], Y[i], X[j], Y[j]);
            if (dist < menor)
                menor = dist;
        }
    }

    if (menor < DISTANCIA_MINIMA)
        printf("%.4f\n", menor);
    else
        cout << "INFINITY \n";

}


int main() {

    int N;

    // Fazer os casos de teste enquanto N não for 0
    do {
        cin >> N;

        if (N > 0)
            casoDeTeste(N);

    } while (N != 0);

    return 0;
}