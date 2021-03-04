// Grafos implementados por lista de adjacẽncia

#include <iostream>

using namespace std;

class Grafo;
class GrafoDirecionado;
class GrafoPonderado;
class Grafo;

class ListaVerticesPonderados;
class ListaVertices;
class Vertice;
class VerticePonderado;

class ListaArestasPonderadas;
class ListaArestas;

class ArestaPonderada;
class Aresta;

/** Grafo direcionado não ponderado **/

/** Grafo não direcionado não ponderado **/

/** Grafo direcionado ponderado **/

class Grafo {
    public:
        bool direcional;
        ListaVerticesPonderados *vertices;
        Grafo(bool direcional);
        void incluirVertice(int valor);
        void incluirAresta(int inicio, int fim, int peso);
        void incluirAresta(int v1, int v2);
};

class ListaVerticesPonderados {
    public:
        VerticePonderado *primeiro;
        VerticePonderado *ultimo;
        ListaVerticesPonderados();
        VerticePonderado *add(VerticePonderado *v);
};

class VerticePonderado {
    public:
        int valor;
        VerticePonderado *prox;
        ListaArestasPonderadas *arestas;
        VerticePonderado(int valor);
};

class ListaArestasPonderadas {
    public:
        ArestaPonderada *primeira;
        ArestaPonderada *ultima;
        ListaArestasPonderadas();
        ArestaPonderada *add(ArestaPonderada *a);
};

class ArestaPonderada {
    public:
        int valor;
        ArestaPonderada *prox;
        VerticePonderado *inicio;
        VerticePonderado *fim;
        ArestaPonderada(int valor, VerticePonderado *i, VerticePonderado *f);
};