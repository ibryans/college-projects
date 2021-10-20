/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * AS01 - Soma de Fatoriais
 */

#include <iostream>
#include <stdio.h>
using namespace std;


/** fatorial usando recursividade **/
long fatorial(int n) {
	if (n > 0) return n * fatorial(n-1);
	else return 1;
}

int main() {
	int m, n;

	/** Ler a entrada até o EOF **/
	while(cin >> m && cin >> n) {
		/** Tratando limites da entrada **/
		if (m >= 0 && m <= 20 && n >= 0 && n <= 20)
			cout << fatorial(m) + fatorial(n) << "\n";
	}

	return 0;
}
