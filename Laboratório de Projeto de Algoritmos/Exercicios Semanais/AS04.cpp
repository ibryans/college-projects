/**
 * @author Bryan Santos Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS04 - Roteadores
 * 
 * Complexidade: O(n^3)
 * Dois for's de 1 a N aninhados (n²) dentro de um while que vai de 1 a N (n² x n) = n^3
 */

#include <iostream>
#include <stdio.h>

// Quantidade máxima de roteadores (vértices do grafo)
#define MAX 60

// Variável global de subconjuntos para o disjoint-union
int sub[MAX];

using namespace std;

/** Implementando funções do método de 'disjoint-union', para poder desenvolver o algoritmo de kruskal **/ 

int buscaSubsets(int i) {
    while(sub[i])
        i = sub[i];
    return i;
}

int uneSubsets(int i, int j) {
    int status = 0;
    if (i != j) {
        sub[j] = i;
        status = 1;
    }
    return status;
}

/** Algoritmo de kruskal
 ** Acha a ávore geradora mínima (MST) de um grafo
 ** No caso deste problema, isso será utilizado para calcular o custo total com cabos após as modificações da empresa (custo da MST) 
 ** @param grafo: O grafo em matriz de adjacência
 ** @param n: A quantidade de vértices
 */
void kruskal(int grafo[MAX][MAX], int n) {
    int custo = 0;
    int arestas = 1;

	// Percorre todo o grafo
    while (arestas < n) {

        int min = 999; 
        int a = 0, b = 0;

		// Pega o menor custo (aresta de menor peso) e seus vértices
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (grafo[i][j] < min && i != j) {
                    min = grafo[i][j];
                    a = i;
                    b = j;
                }
            }
        }

		// Busca pelos subconjuntos relacionados à esses vértices
        int u = buscaSubsets(a);
        int v = buscaSubsets(b);

		// Se forem diferentes (return = 1 = true), é porque não formaram um ciclo
        if (uneSubsets(u, v)) {
            custo += min;
			arestas++;
        }

        grafo[a][b] = grafo[b][a] = 999;
    }

    cout << custo << "\n";
}

int main() {

    // Grafo implementado por matriz de adjacências
    int grafo[MAX][MAX];

    // Número de roteadores (vértices)
    int nR;

    // Número de cabos (arestas)
    int nC;

    cin >> nR;
    cin >> nC;

	// Inicializando o grafo com valores altos (para as condições)
    for (int i = 1; i <= nR; i++) {
        for (int j = 1; j <= nR; j++) {
            grafo[i][j] = 999;
        }
    }

    // Salvando as conexões entre os roteadores (vertices) no grafo
    for (int i = 0; i < nC; i++) {
        int r1, r2, custo;
        cin >> r1;
        cin >> r2;
        cin >> custo;

        if (r1 > 0 && r1 <= nR && r2 > 0 && r2 <= nR && custo < 10000) {
            grafo[r1][r2] = custo;
			grafo[r2][r1] = custo;
        }
    }

    kruskal(grafo, nR);

    return 0;
}