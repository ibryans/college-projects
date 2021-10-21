/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS06 - Penalização
 */

#include <iostream>
#include <stdio.h>
using namespace std;


/** Representação do grafo em matriz de incidência **/
class Grafo {
    int v;  // Número de vértices
    int e;  // Número de arestas

    // Variável de visitas para a busca em profundidade
    bool *visitados;

    int **matriz;

    public:

        /** Inicializador do grafo **/
        Grafo(int v, int e) {
            this->v = v;
            this->e = e;
            this->matriz = new int*[v];
            // this->visitados = new bool[v];
            for (int i = 0; i < v; i++) {
                this->matriz[i] = new int[v];
                // this->visitados[i] = false;
                for (int j = 0; j < v; j++)
                    this->matriz[i][j] = 0;
            }
        }

        /** 
         * Adiciona uma aresta entre 2 vértices no grafo (não direcionado)
         * @param l1: Letra do primeiro vértice
         * @param l2: Letra do segundo vértice
         */
        void addAresta(int v1, int v2, int tempo) {
            cout << v1 << " --> " << v2 << ": " << tempo << '\n';
            this->matriz[v1][v2] = tempo;
            this->matriz[v2][v1] = tempo;
        }

        void percorreMenorCusto() {
            cout << "\nComeçando o percurso:\n\n";
            this->percorreMenorCusto(0,0);
        }

        void percorreMenorCusto(int inicio, int somaCusto) {
            int menor = 0;
            int menorCusto = 9999   ;
            
            // Variável temporária pro tamanho do vetor
            int temp = v;

            cout << inicio << " --> ";

            while(temp > 0) {

                // Pegando o vértice mais próximo (menor custo)
                for (int i = 0; i < v; i++) {
                    if (matriz[inicio][i] != 0 && matriz[inicio][i] < menorCusto)
                        menor = i;
                }

                somaCusto += matriz[inicio][menor];
                this->percorreMenorCusto(menor, somaCusto);

                temp--;
                matriz[inicio][menor] = 9999;
            }
        }

};

int main() {

    int T;

    cin >> T;

    // Para cada instância (T)
    for (int i = 0; i < T; i++) {
        int N, M, K;
        cin >> N;
        cin >> M; 
        cin >> K;

        // Cria um grafo com N vértices e M arestas
        Grafo grafo(N, M);

        // Para cada aresta (M), salva v1, v2 e o custo (tempo)
        for (int j = 0; j < M; j++) {
            int A, B, C;
            cin >> A; 
            cin >> B; 
            cin >> C;

            grafo.addAresta(A-1, B-1, C);
        }

        cout << "Arestas adicionadas\n";    

        // Algoritmo para percorrer todos os vértices com o menor custo
        // Se não houver caminho entre dois vértices, tem que fazer um teletransporte
        // Se o número de teletransportes for maior que K, printa -1
        grafo.percorreMenorCusto();
    }

    return 0;
}