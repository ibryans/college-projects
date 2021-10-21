/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS05 - Componentes Conexos
 */

#include <iostream>
#include <stdio.h>
using namespace std;

/**
 * Retorna o índice correspondente à letra informada 
 * (a = 0, b = 1, c = 2)
 * @param letter
 */
int letterToIndex(char letter) {
    return letter - 97;
}

/**
 * Função que retorna a letra correspondente ao index da matriz 
 * (0 = a, 1 = b, 2 = c)
 * @param index
 */
char indexToLetter(int index) {
    return index + 97;
}

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
            this->visitados = new bool[v];
            for (int i = 0; i < v; i++) {
                this->matriz[i] = new int[v];
                this->visitados[i] = false;
                for (int j = 0; j < v; j++)
                    this->matriz[i][j] = 0;
            }
        }

        /** 
         * Adiciona uma aresta entre 2 vértices no grafo (não direcionado)
         * @param l1: Letra do primeiro vértice
         * @param l2: Letra do segundo vértice
         */
        void addAresta(char l1, char l2) {
            int i_l1 = letterToIndex(l1);
            int i_l2 = letterToIndex(l2);
            this->matriz[i_l1][i_l2] = 1;
            this->matriz[i_l2][i_l1] = 1;
        }

        /**
         * Mostra a quantidade de componentes conexos e seus vértices correspondentes
         * (Utilizando busca em profundidade)
         */
        void componentesConexos() {
            int componentes = 0;

            // Faz uma busca em profundidade em cada vértice (se este ainda não foi visitado)
            for (int i = 0; i < this->v; i++) {
                if (!visitados[i]) {
                    // Se ainda não foi visitado, começa um novo componente.
                    componentes++;
                    this->buscaProfundidade(i);
                    cout << "\n";
                }
            }

            cout << componentes << " connected components\n";
        }

        void buscaProfundidade(int inicio) {
            
            // Mostra o nó que está sendo percorrido durante a busca
            cout << indexToLetter(inicio) << ",";
            
            visitados[inicio] = true;

            // Verifica cada nó adjacente que ainda não foi visitado
            for(int i = 0; i < this->v; i++) {
                if (this->matriz[inicio][i] == 1 && !visitados[i]) {
                    // Se o nó (i) é vizinho do nó (inicio), faz a busca a partir dele
                    this->buscaProfundidade(i);
                }
            }
        }
};


int main() {

    int n;      // casos de teste
    int v, e;   // nº de vértices e arestas

    cin >> n;

    for (int cases = 0; cases < n; cases++) {

        cin >> v;
        cin >> e;

        Grafo grafo(v, e);

        for (int i = 0; i < e; i++) {
            char l1, l2;
            cin >> l1;
            cin >> l2;

            // Marca a aresta entre l1 e l2 no grafo
            grafo.addAresta(l1, l2);
        }

        cout << "Case " << "#" << cases+1 << ": \n";

        /** Conta os componentes do grafo usando busca em profundidade **/
        grafo.componentesConexos();

        cout << "\n";
    }
    

    return 0;
}