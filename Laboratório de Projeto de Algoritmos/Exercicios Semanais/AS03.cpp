/**
 * @author Bryan Santos Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS03 - Subsequências
 * 
 * Complexidade: O(n). 
 * O pior caso seria percorrer toda a primeira sequencia de tamanho N, seja porque a segunda tem o mesmo tamanho N ou porque para achá-la foi preciso percorrer até o final.
 */

#include <iostream>
#include <stdio.h>

using namespace std;

/**
 * Função que verifica se a segunda sequencia é subsequencia da primeira
 * @param s1: Sequência principal
 * @param s2: Sequencia a ser verificada
 * @return boolean indicando true ou false
 */
bool verificaSubsequencia(string s1, string s2) {
	int j = 0;
	bool resultado = false;

	for (int i = 0; i < s1.length(); i++) {

		// Se achou um caracter da s2, avança para o próximo
		if (s1.at(i) == s2.at(j)) {
			j++;
		}

		// Chegou ao final da s2, ela é uma subsequencia de s1
		if (j == s2.length()) {
			resultado = true;
			break;
		}
	}

	return resultado;
}

int main() {

	int testes, queries;
	
	// Casos de teste
	cin >> testes;
	for (int i = 0; i < testes; i++) {
		string sequencia;
		cin >> sequencia;
		
		// Queries
		cin >> queries;
		for (int j = 0; j < queries; j++) {
			string query;
			cin >> query;
			
			// Verificando se 'query' é uma subsequencia de 'sequencia'
			bool resultado = verificaSubsequencia(sequencia, query);
			
			if (resultado) 
				cout << "Yes\n";
			else 
				cout << "No\n";
		}
	}

	return 0;
}
