#include <iostream>
#include "Grafos.hpp"

Grafo::Grafo(bool direcional) {
    this->vertices = new ListaVertices();
    this->direcional = direcional;
}
GrafoP::GrafoP(bool direcional) {
    this->vertices = new ListaVerticesP();
    this->direcional = direcional;
}

void Grafo::incluirVertice(int valor) {
    Vertice *v = new Vertice(valor);
    this->vertices->add(v);
}

void GrafoP::incluirVertice(int valor) {
    VerticeP *v = new VerticeP(valor);
    this->vertices->add(v);
}

void Grafo::incluirAresta(int v1, int v2) {
    Vertice *i, *f;

    for (Vertice *c = this->vertices->primeiro; c != NULL; c = c->prox) {
        if (c->valor == v1) {
            i = c;
        }
        if (c->valor == v2) {
            f = c;
        }
    }

    i->arestas->add(new Aresta(i, f));

    // Se o grafo não é direcional, inclui a aresta no segundo vértice também
    if (this->direcional == false)
        f->arestas->add(new Aresta(f, i));
}

void GrafoP::incluirAresta(int v1, int v2, int peso) {
    VerticeP *i, *f;

    for (VerticeP *c = this->vertices->primeiro; c != NULL; c = c->prox) {
        if (c->valor == v1) {
            i = c;
        }
        if (c->valor == v2) {
            f = c;
        }
    }

    i->arestas->add(new ArestaP(peso, i, f));

    // Se o grafo não é direcional, inclui a aresta no segundo vértice também
    if (this->direcional == false)
        f->arestas->add(new ArestaP(peso, f, i));
}

Aresta::Aresta(Vertice *i, Vertice *f) {
    this->inicio = i;
    this->fim = f;
    this->prox = NULL;
}

ArestaP::ArestaP(int valor, VerticeP *i, VerticeP *f) {
    this->valor = valor;
    this->inicio = i;
    this->fim = f;
    this->prox = NULL;
}

ListaArestas::ListaArestas() {
    this->primeira = NULL;
    this->ultima = this->primeira;
};

ListaArestasP::ListaArestasP() {
    this->primeira = NULL;
    this->ultima = this->primeira;
};

Vertice::Vertice(int valor) {
    this->valor = valor;
    this->prox = NULL;
    this->arestas = new ListaArestas();
}

VerticeP::VerticeP(int valor) {
    this->valor = valor;
    this->prox = NULL;
    this->arestas = new ListaArestasP();
}

ListaVertices::ListaVertices() {
    this->primeiro = NULL;
    this->ultimo = this->primeiro;
};

ListaVerticesP::ListaVerticesP() {
    this->primeiro = NULL;
    this->ultimo = this->primeiro;
};

/** Função de adicionar elemento na lista de vértices **/
Vertice *ListaVertices::add(Vertice *v) {
    if (this->ultimo == NULL) {
        this->primeiro = v;
        this->ultimo = this->primeiro;
    } else {
        this->ultimo->prox = v;
        this->ultimo = this->ultimo->prox;
    }
    return this->ultimo;
}

/** Função de adicionar elemento na lista de vértices com arestas ponderadas **/
VerticeP *ListaVerticesP::add(VerticeP *v) {
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
Aresta *ListaArestas::add(Aresta *a) {
    if (this->ultima == NULL) {
        this->primeira = a;
        this->ultima = this->primeira;
    } else {
        this->ultima->prox = a;
        this->ultima = this->ultima->prox;
    }
    return this->ultima;
}

/** Função de adicionar elementos na lista de arestas ponderadas **/
ArestaP *ListaArestasP::add(ArestaP *a) {
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

    Grafo *grafoSimples = new Grafo(false);
    Grafo *grafoDirecionado = new Grafo(true);
    GrafoP *grafoDirecionadoPonderado = new GrafoP(true);
    GrafoP *grafoPonderado = new GrafoP(false);

    grafoDirecionado->incluirVertice(1);
    grafoDirecionado->incluirVertice(2);
    grafoDirecionado->incluirVertice(3);
    grafoDirecionado->incluirVertice(4);

    grafoSimples->incluirVertice(1);
    grafoSimples->incluirVertice(2);
    grafoSimples->incluirVertice(3);
    grafoSimples->incluirVertice(4);

    grafoDirecionadoPonderado->incluirVertice(1);
    grafoDirecionadoPonderado->incluirVertice(2);
    grafoDirecionadoPonderado->incluirVertice(3);
    grafoDirecionadoPonderado->incluirVertice(4);

    grafoPonderado->incluirVertice(1);
    grafoPonderado->incluirVertice(2);
    grafoPonderado->incluirVertice(3);
    grafoPonderado->incluirVertice(4);

    grafoSimples->incluirAresta(1, 3);
    grafoSimples->incluirAresta(3, 4);

    grafoDirecionado->incluirAresta(1, 3);
    grafoDirecionado->incluirAresta(3, 4);

    grafoDirecionadoPonderado->incluirAresta(1, 3, 20);
    grafoDirecionadoPonderado->incluirAresta(3, 4, 12);

    grafoPonderado->incluirAresta(1, 3, 20);
    grafoPonderado->incluirAresta(3, 4, 12);

    cout << "Criando os vértices 1,2,3,4 e as arestas 1->3, 3->4 para todos 4 tipos de grafo\nObserve a diferença :)\n\n";

    cout << "\nGrafo direcionado e não ponderado:\n";
    for (Vertice *i = grafoDirecionado->vertices->primeiro; i != NULL; i = i->prox) {
        cout << "(" << i->valor << ") ";
        for (Aresta *a = i->arestas->primeira; a != NULL; a = a-> prox)
            cout << "-> " << a->fim->valor << " ";
        cout << "\n";
    }

    cout << "\nGrafo não direcionado e não ponderado:\n";
    for (Vertice *i = grafoSimples->vertices->primeiro; i != NULL; i = i->prox) {
        cout << "(" << i->valor << ") ";
        for (Aresta *a = i->arestas->primeira; a != NULL; a = a-> prox)
            cout << "-> " << a->fim->valor << " ";
        cout << "\n";
    }

    cout << "\nGrafo direcionado e ponderado:\n";
    for (VerticeP *i = grafoDirecionadoPonderado->vertices->primeiro; i != NULL; i = i->prox) {
        cout << "(" << i->valor << ") ";
        for (ArestaP *a = i->arestas->primeira; a != NULL; a = a-> prox)
            cout << "-> " << a->fim->valor << " (peso = " << a->valor << ") ";
        cout << "\n";
    }

    cout << "\nGrafo não direcionado e ponderado:\n";
    for (VerticeP *i = grafoPonderado->vertices->primeiro; i != NULL; i = i->prox) {
        cout << "(" << i->valor << ") ";
        for (ArestaP *a = i->arestas->primeira; a != NULL; a = a-> prox)
            cout << "-> " << a->fim->valor << " (peso = " << a->valor << ") ";
        cout << "\n";
    }

    cout << "\n";

    return 0;
}
