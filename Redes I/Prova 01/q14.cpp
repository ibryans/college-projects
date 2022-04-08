#include <iostream>

using namespace std;

int main() {

	string mensagem;
	cin >> mensagem;
	
	int contadorUm = 0;
	string novaMensagem = "7E";

	for (int i = 0; i < mensagem.length(); i++) {
		novaMensagem += mensagem.at(i);

		if (mensagem.at(i) == '1')
			contadorUm++;

		if (contadorUm == 5) {
			novaMensagem += "0" + mensagem.at(i);
			contadorUm = 0;
		}
	}

	novaMensagem += "7E";

	cout << "\n\n" << novaMensagem << "\n";

	return 0;
}
