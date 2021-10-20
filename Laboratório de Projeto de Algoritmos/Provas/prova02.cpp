/**
 * @author Bryan S. Oliveira
 * Laboratório de Projeto de Algoritmos
 * EX02 - Prova Intermediária
 * Complexidade: 
 */

#include <iostream>
#include <stdio.h>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

#define MAX 999

// Vetor usado no union find
int pre[MAX];

// Estrutura para conexão entre estações
struct edge {
    int station1;
    int station2;
    int ticketCost;
} typedef Edge;


int findset(int r) {
    if (pre[r] == r) 
        return r; // x is the root
    return findset(pre[r]);
}

void testCase(int s, int c) {

    // Um map de string pra int com o nome - índice das estações
    map<string, int> stations;

    // Conexões entre estações
    vector<Edge> connections;

    // Salvando os nomes e índices das estações
    for (int i = 0; i < s; i++) {
        string name;
        cin >> name;
        stations[name] = i;
    }

    // Salvando as conexões
    for (int i = 0; i < c; i++) {
        string s1, s2;
        Edge connection;
        int cost;

        cin >> s1 >> s2 >> cost;

        connection.station1   = stations[s1];
        connection.station2   = stations[s2];
        connection.ticketCost = cost;

        connections.push_back(connection);
    }

    string start;
    cin >> start;

    for (int i = 1; i <= s; i++)
        pre[i] = i;
    
    // Usando um algoritmo para ordenar as conexões da mais barata para a mais cara
    sort(connections.begin(), connections.end());

    int cot = s-1, sum = 0;

    for(int i = 0; i < c; i++) {
        int u = findset(connections[i].station1);
        int v = findset(connections[i].station2);

        if(u != v) {
            pre[u] = v;
            cot--;
            sum += connections[i].ticketCost;
        }
    }
    
    if (cot) 
        cout << "Impossible\n";
    else 
        cout << sum << "\n";

}

int main() {

    int nStations, nConnections;

    do {

        cin >> nStations; 
        cin >> nConnections;

        if (nStations > 0 && nConnections > 0)
            testCase(nStations, nConnections);

    } while (nStations > 0 && nConnections > 0);

    return 0;
}