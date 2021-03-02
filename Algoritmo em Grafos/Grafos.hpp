// Grafos implementados por lista de adjacẽncia

#include <iostream>

using namespace std;

class GrafoDirecionadoPonderado {
    public:
        ListaVerticesPonderados *vertices;
        void incluirAresta(VerticePonderado *pai, VerticePonderado *filho, char rotulo);
};

class ListaVerticesPonderados {
    private:
        VerticePonderado *primeiro;
        VerticePonderado *ultimo;
    public:
        ListaVerticesPonderados();
        VerticePonderado *add(VerticePonderado *v);
};

class VerticePonderado {
    public:
        char rotulo;
        VerticePonderado *prox;
        ListaArestasPonderadas *arestas;
        VerticePonderado(char *rotulo);
};

class ListaArestasPonderadas {
    private:
        ArestaPonderada *primeira;
        ArestaPonderada *ultima;
    public:
        ListaArestasPonderadas();
        void add(ArestaPonderada *a);
};

class ArestaPonderada {
    public:
        char rotulo;
        ArestaPonderada *prox;
        VerticePonderado *inicio;
        VerticePonderado *fim;
        ArestaPonderada(char rotulo, VerticePonderado *i, VerticePonderado *f);
};