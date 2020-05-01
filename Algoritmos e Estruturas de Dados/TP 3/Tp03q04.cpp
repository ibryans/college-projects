#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 1000
#define TRUE 1
#define FALSE 0

/****************** PERSONAGEM ******************/

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

char *pegarAtributo(char json[], char atributo[]) {

    json[strlen(json)-1] == '\0';

    int achado = FALSE;
    char *valor;
    int i = 0;

    // Vai até o atributo escolhido
    while (achado == FALSE && i < strlen(json)) {

        // Verifica se é aspas simples (39 = ')
        if (json[i] == 39) {

            i++;
            char nome[20] = " ";
            int count = 0;

            // Percorrer o conteudo dentro das aspas simples e salva em nome
            while (i < strlen(json) && json[i] != 39) {
                nome[count] = json[i];
                i++; count++;
            }

            // Se o nome achado for o atributo escolhido
            if (strcmp(nome, atributo) == 0) {
                achado = TRUE;
                valor = (char*) malloc (strlen(nome) + sizeof(char));
            }

        } else {
            i++;
        }
    }

    // Se saiu do while e não achou o atributo
    if (achado == FALSE) {
        printf("Atributo não encontrado!\n");
    } else {

        // Vai pro começo do valor do atributo
        i += 4;

        // Cria um contador pra colocar caracteres em 'valor'
        int j = 0;

        while (i < strlen(json) && json[i] != 39) {
            valor[j] = json[i];
            i++; j++;
        }
    }

    return strdup(valor);
}

Personagem ler(char *path) {

   path[strlen(path)-1] = '\0';

   FILE *arq;
   char json[MAX];

   Personagem p;

   if ((arq = fopen(path, "r")) != NULL) {

      // Salvando o conteudo do arquivo na string json   
      fgets(json, MAX, arq);

      strcpy(p.nome, pegarAtributo(json, (char*)"name"));

      char *altura = pegarAtributo(json, (char*)"height");
      p.altura = (strcmp(altura, "unknown") == 0) ? 0 : atoi(altura);

      char *peso = pegarAtributo(json, (char*)"mass"); 
      p.peso = (strcmp(peso, "unknown") == 0) ? 0 : atof(peso);

      strcpy(p.corDoCabelo, pegarAtributo(json, (char*)"hair_color"));
      strcpy(p.corDaPele, pegarAtributo(json, (char*)"skin_color"));
      strcpy(p.corDosOlhos, pegarAtributo(json, (char*)"eye_color"));
      strcpy(p.anoNascimento, pegarAtributo(json, (char*)"birth_year"));
      strcpy(p.genero, pegarAtributo(json, (char*)"gender"));
      strcpy(p.homeworld, pegarAtributo(json, (char*)"homeworld"));

      fclose(arq);
   }

   else printf("Arquivo nulo\n");

   return p;
}

void imprimir(Personagem p) {
    printf(" ## %s", p.nome);
    printf(" ## %d", p.altura);
    printf(" ## %g", p.peso);
    printf(" ## %s", p.corDoCabelo);
    printf(" ## %s", p.corDaPele);
    printf(" ## %s", p.corDosOlhos);
    printf(" ## %s", p.anoNascimento);
    printf(" ## %s", p.genero);
    printf(" ## %s ##\n", p.homeworld);
}

int isNotEnd(char *entry) {
    return (strlen(entry) >= 4 && (entry[0] == 'F' && entry[1] == 'I' && entry[2] == 'M'))
    ? FALSE
    : TRUE;
}

/***************************** CÉLULA *****************************/

typedef struct CelulaDupla {
	Personagem elemento;
	CelulaDupla* prox;
   CelulaDupla* ant;
} CelulaDupla;

CelulaDupla* new_CelulaDupla(Personagem elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}


/************************** LISTA  *****************************/

CelulaDupla* primeiro;
CelulaDupla* ultimo;


/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start () {
   Personagem p;
   primeiro = new_CelulaDupla(p);
   ultimo = primeiro;
}


/**
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Personagem x) {
   CelulaDupla* tmp = new_CelulaDupla(x);

   tmp->ant = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro == ultimo) {                    
      ultimo = tmp;
   } else {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
}


/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Personagem x) {
   ultimo->prox = new_CelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
}


/**
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Personagem removerInicio() {
   if (primeiro == ultimo) {
      printf("Erro ao remover (vazia)!");
   }

   CelulaDupla* tmp = primeiro;
   primeiro = primeiro->prox;
   Personagem resp = primeiro->elemento;
   tmp->prox = primeiro->ant = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}


/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Personagem removerFim() {
   if (primeiro == ultimo) {
      printf("Erro ao remover (vazia)!");
   } 
   Personagem resp = ultimo->elemento;
   ultimo = ultimo->ant;
   ultimo->prox->ant = NULL;
   free(ultimo->prox);
   ultimo->prox = NULL;
   return resp;
}


/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}


/**
 * Insere um elemento em uma posicao especifica considerando que o 
 * primeiro elemento valido esta na posicao 0.
 * @param x int elemento a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Personagem x, int pos) {

   int tam = tamanho();

   if(pos < 0 || pos > tam){
      printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      inserirInicio(x);
   } else if (pos == tam){
      inserirFim(x);
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = primeiro;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      CelulaDupla* tmp = new_CelulaDupla(x);
      tmp->ant = i;
      tmp->prox = i->prox;
      tmp->ant->prox = tmp->prox->ant = tmp;
      tmp = i = NULL;
   }
}


/**
 * Remove um elemento de uma posicao especifica da lista
 * considerando que o primeiro elemento valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp int elemento a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Personagem remover(int pos) {
   Personagem resp;
   int tam = tamanho();

   if (primeiro == ultimo){
      printf("Erro ao remover (vazia)!");
   } else if(pos < 0 || pos >= tam){
      printf("Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      resp = removerInicio();
   } else if (pos == tam - 1){
      resp = removerFim();
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = primeiro->prox;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      i->ant->prox = i->prox;
      i->prox->ant = i->ant;
      resp = i->elemento;
      i->prox = i->ant = NULL;
      free(i);
      i = NULL;
   }

   return resp;
}


/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar() {
   CelulaDupla* i;
   for (i = primeiro->prox; i != NULL; i = i->prox) {
      imprimir(i->elemento);
   }
}


void swap(CelulaDupla *a, CelulaDupla *b) {
   Personagem temp = a->elemento;
   a->elemento = b->elemento;
   b->elemento = temp;
}

Personagem pegarPivo(CelulaDupla *esq, CelulaDupla *dir) {
   int tam = 0;
   int i = 0;
   CelulaDupla *celulaPivo;
   for(CelulaDupla *tmp = esq; tmp != dir->prox; tmp = tmp->prox, tam++);
   for (celulaPivo = esq; i < tam/2; celulaPivo = celulaPivo->prox, i++);
   return celulaPivo->elemento;
}

void r_quicksort(CelulaDupla *esq, CelulaDupla *dir) {
    CelulaDupla *i = esq;
    CelulaDupla *j = dir;
    Personagem pivo = pegarPivo(esq, dir);

    while (i != NULL && j != NULL && i <= j) {
        while (strcmp(i->elemento.corDoCabelo, pivo.corDoCabelo) < 0) {
            i = i->prox;
        }
        while (strcmp(j->elemento.corDoCabelo, pivo.corDoCabelo) > 0) {
            j = j->ant;
        }
        if (i <= j) {
            swap(i, j);
            i = i->prox;
            j = j->ant;
        }
    }

   if (esq < j && j != NULL)
      r_quicksort(esq, j);

   if (i < dir && i != NULL)
      r_quicksort(i, dir);
}

void quicksort() {
    r_quicksort(primeiro->prox, ultimo);
}

/**
 * Ordena os personagens empatados por inserção (usando o nome)
 */
void desempataPorInsercao(CelulaDupla *comeco, CelulaDupla *limite) {
   for (CelulaDupla *i = comeco->prox; i != limite; i = i->prox) {
      Personagem temp = i->elemento;
      CelulaDupla *j = i->ant;
      while(j != comeco->ant && strcmp(j->elemento.nome, temp.nome) > 0) {
            j->prox->elemento = j->elemento;
            j = j->ant;
      }
      j->prox->elemento = temp;
   }
}

void desempate() {
   char *corEmpatada = (char*) malloc (sizeof(char));
   CelulaDupla *i = primeiro->prox;
   CelulaDupla *j = primeiro->prox;

   while (i != NULL) {
      corEmpatada = i->elemento.corDoCabelo;
      while (j != NULL && strcmp(j->elemento.corDoCabelo, corEmpatada)==0) {
            j = j->prox;
      }
      desempataPorInsercao(i, j);
      i = j;
   }
}

int main(int argc, char const *argv[]) {
    
   int n = 0;
   Personagem lista[MAX];
   char entry[50];

   start();

   do {

      fgets(entry, 50, stdin);

      if (isNotEnd(entry) == TRUE) {
         lista[n] = ler(entry);
         inserirFim(lista[n]);
         n++;
      }

   } while (isNotEnd(entry) == TRUE);

   // ordena por quicksort
   quicksort();

   // desempata os personagens com mesma cor de cabelo (por nome)
   desempate();

   mostrar();

   return 0;
}
