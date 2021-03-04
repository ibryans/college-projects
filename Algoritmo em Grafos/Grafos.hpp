// Grafos implementados por lista de adjacẽncia

#include <iostream>

using namespace std;

class Grafo;
class GrafoP;

class ListaVerticesP;
class ListaVertices;

class Vertice;
class VerticeP;

class ListaArestasP;
class ListaArestas;

class ArestaP;
class Aresta;

/** Grafo não ponderado **/
class Grafo {
    public:
        bool direcional; // Indica se o grafo é direcional ou não
        ListaVertices *vertices;
        Grafo(bool direcional);
        void incluirVertice(int valor);
        void incluirAresta(int v1, int v2);
};

/** Grafo ponderado **/
class GrafoP {
    public:
        bool direcional; // Indica se o grafo é direcional ou não
        ListaVerticesP *vertices;
        GrafoP(bool direcional);
        void incluirVertice(int valor);
        void incluirAresta(int v1, int v2, int peso);
};

/** Lista de vértices com arestas ponderadas **/
class ListaVerticesP {
    public:
        VerticeP *primeiro;
        VerticeP *ultimo;
        ListaVerticesP();
        VerticeP *add(VerticeP *v);
};

/** Lista de vértices com arestas simples **/
class ListaVertices {
    public:
        Vertice *primeiro;
        Vertice *ultimo;
        ListaVertices();
        Vertice *add(Vertice *v);
};

/** Vértice com arestas ponderadas **/
class VerticeP {
    public:
        int valor;
        VerticeP *prox;
        ListaArestasP *arestas;
        VerticeP(int valor);
};

/** Vértice com arestas simples **/
class Vertice {
    public:
        int valor;
        Vertice *prox;
        ListaArestas *arestas;
        Vertice(int valor);
};

/** Lista Arestas ponderadas **/
class ListaArestasP {
    public:
        ArestaP *primeira;
        ArestaP *ultima;
        ListaArestasP();
        ArestaP *add(ArestaP *a);
};

/** Lista Arestas simples **/
class ListaArestas {
    public:
        Aresta *primeira;
        Aresta *ultima;
        ListaArestas();
        Aresta *add(Aresta *a);
};

/** Aresta ponderada **/
class ArestaP {
    public:
        int valor;
        ArestaP *prox;
        VerticeP *inicio;
        VerticeP *fim;
        ArestaP(int valor, VerticeP *i, VerticeP *f);
};

/** Aresta simples **/
class Aresta {
    public:
        Aresta *prox;
        Vertice *inicio;
        Vertice *fim;
        Aresta(Vertice *i, Vertice *f);
};