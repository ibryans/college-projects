/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS08 - Bolhas e Baldes

 * A ideia aqui é o contar o número de inversões de um vetor, porque, dada a sequência gerada pelo computador de Andrea, se criarmos um algoritmo que ordene o vetor contando o número de trocas (inverter um valor maior com um menor, pra deixar o vetor na ordem crescente), podemos saber quem ganhará o jogo, Marcelo ou Carlos
 
 * Portanto, utilizei o algoritmo MergeSort um pouco modificado, para poder contar essa quantidade de inversões.
 * Complexidade: O(n * log(n))
 */

#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;

#define MARCELO 1
#define CARLOS 2
#define MAX 9999    // Um número grande para fins de comparação

void confereVencedor(int numInversoes) {
    // Se o número de inversões for par, quer dizer que Carlos ganhou o jogo
    if (numInversoes % 2 == 0) {
        cout << "Carlos\n";
    }

    // Se o número for impar, quer dizer que Marcelo ganhou (ele sempre começa jogando)
    else {
        cout << "Marcelo\n";
    }
}

/**
 * Função que calcula o número de inversões do vetor utilizando o algoritmo de ordenação Mergesort
 * @param sequencia - O vetor do problema (sequencia de números)
 * @return O número de inversões
 */
int mergeSort(vector<int> &sequencia) {
	
	int inversoes = 0;
	
	// Se a sequencia tiver uma posição apenas, não há inversões
	if (sequencia.size() == 1) 
        return 0;
	
    // Vetores temporários para fazer a ordenação (vetor original dividido em dois)
	vector<int> v1;
    vector<int> v2;

	for (int i = 0; i < sequencia.size() / 2; i++){
		v1.push_back(sequencia[i]);
	}
	
	for (int i = sequencia.size() / 2; i < sequencia.size(); i++){
	 	v2.push_back(sequencia[i]);
    }

	// A ideia aqui é ordenar cada metade da sequencia e somar o número de inversões
	inversoes += mergeSort(v1);
	inversoes += mergeSort(v2);

    // Se n quebrar nada, pode tirar
	v1.push_back(MAX);
	v2.push_back(MAX);
	
	int indexV1 = 0;
    int indexV2 = 0;
	
    // Inserindo todos os elementos ordenados de volta ao vetor original (em ordem crescente)
	for(int i = 0; i < sequencia.size(); i++){
		
		if (v1[indexV1] <= v2[indexV2]){

			sequencia[i] = v1[indexV1];
			indexV1++;

		} else {
			
			sequencia[i] = v2[indexV2];
			indexV2++;

            // Soma a quantidade de elementos em v2 que são menores que v1 (vai somando o restante das inversões necessárias)
			inversoes += v1.size() - indexV1-1;
		}
	}
	
	return inversoes;
}

/**
 * Função para rodar cada caso de teste do problema
 */
void casoDeTeste(int N) {

    // Criando a sequencia de números
    vector<int> sequencia(N);

    // O número final de inversões
    int resultado;

    for (int i = 0; i < N; i++) {
        // int x;
        // cin >> x;
        // sequencia.push_back(x);
        cin >> sequencia[i];
    }

    resultado = mergeSort(sequencia);

    confereVencedor(resultado);
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