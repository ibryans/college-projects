#include <iostream>
#include "Grafos.hpp"

void GrafoDirecionadoPonderado::incluirAresta(VerticePonderado *pai, VerticePonderado *filho, char rotulo) {
    // VerticePonderado *p = this->vertices->add(pai);
    // VerticePonderado *f = this->vertices->add(filho);
    pai->arestas->add(new ArestaPonderada(rotulo, pai, filho));
}

ListaVerticesPonderados::ListaVerticesPonderados() {}
 
VerticePonderado *ListaVerticesPonderados::add(VerticePonderado *v) {
    this->ultimo->prox = v;
    this->ultimo = this->ultimo->prox;
    return this->ultimo;
}

VerticePonderado::VerticePonderado(char *rotulo) {}

ListaArestasPonderadas::ListaArestasPonderadas() {}
void ListaArestasPonderadas::add(ArestaPonderada *a);

// ArestaPonderada(char rotulo, VerticePonderado *i, VerticePonderado *f);