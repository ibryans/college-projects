#include <iostream>
#include "Grafos.hpp"

ArestaPonderada::ArestaPonderada(int valor, VerticePonderado *i, VerticePonderado *f) {
    this->valor = valor;
    this->inicio = i;
    this->fim = f;
    this->prox = NULL;
}

ListaArestasPonderadas::ListaArestasPonderadas() {
    this->primeira = NULL;
    this->ultima = this->primeira;
};

VerticePonderado::VerticePonderado(int valor) {
    this->valor = valor;
    this->prox = NULL;
    this->arestas = new ListaArestasPonderadas();
}

ListaVerticesPonderados::ListaVerticesPonderados() {
    this->primeiro = NULL;
    this->ultimo = this->primeiro;
};

Grafo::Grafo(bool direcional) {
    this->vertices = new ListaVerticesPonderados();
    this->direcional = direcional;
}

/** Inclui um vértice no grafo direcionado ponderado */
void Grafo::incluirVertice(int valor) {
    VerticePonderado *v = new VerticePonderado(valor);
    this->vertices->add(v);
}

/** Inclui uma aresta no grafo direcionado ponderado */
void Grafo::incluirAresta(int inicio, int fim, int valor) {

    VerticePonderado *i, *f;

    for (VerticePonderado *c = this->vertices->primeiro; c != NULL; c = c->prox) {
        if (c->valor == inicio) {
            i = c;
        }
        if (c->valor == fim) {
            f = c;
        }
    }

    i->arestas->add(new ArestaPonderada(valor, i, f));

    // Se o grafo é direcional, inclui a aresta no segundo vértice também
    if (this->direcional)
        f->arestas->add(new ArestaPonderada(valor, i, f));
}
 
/** Função de adicionar elemento na lista de vértices **/
VerticePonderado *ListaVerticesPonderados::add(VerticePonderado *v) {
    if (this->ultimo == NULL) {
        this->primeiro = v;
        this->ultimo = this->primeiro;
    } else {
        this->ultimo->prox = v;
        this->ultimo = this->ultimo->prox;
    }
    return this->ultimo;
}

/** Função de adicionar elementos na lista de arestas **/
ArestaPonderada *ListaArestasPonderadas::add(ArestaPonderada *a) {
    if (this->ultima == NULL) {
        this->primeira = a;
        this->ultima = this->primeira;
    } else {
        this->ultima->prox = a;
        this->ultima = this->ultima->prox;
    }
    return this->ultima;
}

int main() {

    Grafo *grafo = new Grafo(true);

    grafo->incluirVertice(1);
    grafo->incluirVertice(2);
    grafo->incluirVertice(3);
    grafo->incluirVertice(4);

    grafo->incluirAresta(1, 4, 20);
    grafo->incluirAresta(1, 3, 15);

    for (ArestaPonderada *i = grafo->vertices->primeiro->arestas->primeira; i != NULL; i = i->prox)
    {
        cout << "aresta: (" << i->inicio->valor << ") ------> (" << i->fim->valor << ")\n";
    }
    
    return 0;
}
