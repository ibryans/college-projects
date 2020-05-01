#include <stdio.h>
#define n 100

typedef struct personagem {

    char nome[20],
         corDoCabelo[20],
         corDaPele[20],
         corDosOlhos[20],
         anoNascimento[20],
         genero[20],
         homeworld[20];

    int altura;
    float peso;

} Personagem;

// array de personagens
Personagem array[n];

void constroi(int tamanho){
    for(int i = tamanho; i > 1 && array[i].altura > array[i/2].altura; i /= 2){
        swap(i, i/2);
    }
}

void reconstroi(int esq, int dir){
    int filho;
    while(esq <= (dir/2)){

        if (array[2*esq].altura < array[2*esq+1].altura || 2*esq == dir){
            filho = 2*esq;
        } else {
            filho = 2*esq + 1;
        }

        if(array[esq].altura > array[filho].altura){
            swap(array, esq, filho);
            esq = filho;
        }else{
            esq = dir;
        }
    }
}

void heapsort_parcial(int k) {
    for(int i = n; i > 0; i--){
        array[i] = array[i-1];
    }

    int esq = 1;
    int dir;
    int aux = 0;

    for(int tamanho = 1; tamanho <= n; tamanho++){
        constroi(tamanho);
    }

    dir = n;

    while(aux < k){
        swap(1, n-aux);
        dir--;aux++;
        reconstroi(esq, dir);
    }

    //Alterar o vetor para voltar a posicao zero
    for(int i = 0; i < n; i++){
        array[i] = array[i+1];
    }
}