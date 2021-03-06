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


/************************** LISTA  *****************************/

// Criando a lista
Personagem lista[MAX];
int n;


/**
 * Inicializacoes
 */
void start(){
   n = 0;
}


/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x Personagem a ser inserido.
 */
void inserirInicio(Personagem p) {
   int i;

   //validar insercao
   if(n >= MAX){
      printf("Erro ao inserir!");
      exit(1);
   } 

   //levar elementos para o fim da lista
   for(i = n; i > 0; i--){
      lista[i] = lista[i-1];
   }

   lista[0] = p;
   n++;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param p Personagem a ser inserido.
 */
void inserirFim(Personagem p) {

   //validar insercao
   if(n >= MAX){
      printf("Erro ao inserir!");
      exit(1);
   }

   lista[n] = p;
   n++;
}


/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param p Personagem a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Personagem p, int pos) {
   int i;

   //validar insercao
   if(n >= MAX || pos < 0 || pos > n){
      printf("Erro ao inserir!");
      exit(1);
   }

   //levar elementos para o fim do lista
   for(i = n; i > pos; i--){
      lista[i] = lista[i-1];
   }

   lista[pos] = p;
   n++;
}


/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp Personagem a ser removido.
 */
Personagem removerInicio() {
   int i;
   Personagem resp;

   //validar remocao
   if (n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }

   resp = lista[0];
   n--;

   for(i = 0; i < n; i++){
      lista[i] = lista[i+1];
   }

   return resp;
}


/**
 * Remove um elemento da ultima posicao da 
 * @return resp Personagem a ser removido.
 */
Personagem removerFim() {

   //validar remocao
   if (n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }

   return lista[--n];
}


/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp Personagem a ser removido.
 */
Personagem remover(int pos) {
   int i;
   Personagem resp;

   //validar remocao
   if (n == 0 || pos < 0 || pos >= n) {
      printf("Erro ao remover!");
      exit(1);
   }

   resp = lista[pos];
   n--;

   for(i = pos; i < n; i++){
      lista[i] = lista[i+1];
   }

   return resp;
}


/**
 * Mostra os lista separados por espacos.
 */
void mostrar(){
   for(int i = 0; i < n; i++){
      imprimir(lista[i]);
   }
}

void mostrarParcial(int k){
   for(int i = 0; i < k; i++){
      imprimir(lista[i]);
   }
}


void swap(int a, int b) {
    Personagem temp = lista[a];
    lista[a] = lista[b];
    lista[b] = temp;
}

void constroi(int tamanho){
   for(int i = tamanho; i > 1 && lista[i].altura < lista[i/2].altura; i /= 2){
      swap(i, i/2);
   }
}

void reconstroi(int tamanho){
   int i = 1, filho;
   while (i <= (tamanho/2)) {

      if (lista[2*i].altura > lista[2*i+1].altura || 2*i == tamanho) {
            filho = 2*i;
      } else {
            filho = 2*i + 1;
      }

      if (lista[i].altura < lista[filho].altura){
         swap(i, filho);
         i = filho;
      } else {
         i = tamanho;
      }
   }
}

void heapsortParcial (int k) {
   Personagem tmp[n+1];

   for(int i = 0; i < n; i++)
      tmp[i + 1] = lista[i];

   for(int i = 0; i < n; i++)
      lista[i+1] = tmp[i+1];

   // Contrução do heap
   for(int tamanho = 2; tamanho <= n; tamanho++)
      constroi(tamanho);

   // Fazendo a ordenação até k
   int tamanho = k;
   while(tamanho > 1){
      swap(1, tamanho--);
      reconstroi(tamanho);
   }

   for(int i = 0; i < n; i++)
      tmp[i+i] = lista[i+1];

   for(int i = 0; i < n; i++)
      lista[i] = tmp[i+1];

}


/**
 * Ordena os personagens empatados por inserção (usando o nome)
 */
void desempataPorInsercao(int comeco, int limite) {
   for (int i = comeco+1; i < limite; i++) {
      Personagem temp = lista[i];
      int j = i-1;
      while(j >= 0 && strcmp(lista[j].nome, temp.nome) < 0) {
            lista[j+1] = lista[j];
            j--;
      }
      lista[j+1] = temp;
   }
}

void desempate() {
   int alturaEmpatada = lista[0].altura;
   int i = 0;
   int j = 0;
   while (i < n) {
      alturaEmpatada = lista[j].altura;
      while (j < n && lista[j].altura == alturaEmpatada) {
         j++;
      }
      desempataPorInsercao(i, j);
      i = j;
   }
}

int main(int argc, char const *argv[]) {
    
    int n = 0;
    Personagem lista[MAX];
    char entry[50];

    do {

        fgets(entry, 50, stdin);

        if (isNotEnd(entry) == TRUE) {
            lista[n] = ler(entry);
            inserirFim(lista[n]);
            n++;
        }

    } while (isNotEnd(entry) == TRUE);

    int k = 10;

    heapsortParcial(k);

    desempate();

    mostrarParcial(k);

   return 0;
}
