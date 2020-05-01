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
void mostrar (){
   int i;

   for(i = 0; i < n; i++){
      printf("[%d] ", i);
      imprimir(lista[i]);
   }
}

/**
 * Compara duas strings retornando qual string é menor ou se são iguais
 * @param nome1
 * @param nome2
 * @return 0 se forem iguais, 1 se a primeira string for menor, 2 se a segunda string for menor
 */
int compareNome(char *nome1, char *nome2, int pos) {
    int result = 0;
    if (pos < strlen(nome1) && pos < strlen(nome2)) {
        if (nome1[pos] < nome2[pos]) {
            result = 1;
        }
        else if (nome1[pos] > nome2[pos]) {
            result = 2;
        }
        else {
            result = compareNome(nome1, nome2, pos+1);
        }
    } else if (pos < strlen(nome1)-1) {
        result = 2;
    } else if (pos < strlen(nome2)-1) {
        result = 1;
    }
    return result;
}

// Pesquisa binária recursivo
int r_pesquisaBinaria(char *nome, int esq, int dir) {
    int result = FALSE;

    if (esq <= dir) {
        int meio = (esq+dir)/2;

        switch(compareNome(nome, lista[meio].nome, 0)) {
            // Se forem iguais, achou o resultado
            case 0: result = TRUE;
                    break;  

            // Se o nome for menor que o nome do personagem[meio], pesquisa entre esq e meio
            case 1: result = r_pesquisaBinaria(nome, esq, meio-1); 
                    break;

            // Se o nome for maior, pesquisa na metade entre meio e dir
            case 2: result = r_pesquisaBinaria(nome, meio+1, dir); 
                    break;
        }
    }

    return result;
}

/**
 * Pesquisa por um personagem a partir de seu nome, usando o método de pesquisa binária
 * @param nome
 * @return TRUE(1) ou FALSE(2)
 */
int pesquisaBinaria(char *nome) {
    return r_pesquisaBinaria(nome, 0, n-1);
}


int main(int argc, char const *argv[]) {
    
   int n = 0;
   Personagem personagens[MAX];
   char entry[50];

   do {

      fgets(entry, 50, stdin);

      if (isNotEnd(entry) == TRUE) {
         personagens[n] = ler(entry);
         inserirFim(personagens[n]);
         n++;
      }

   } while (isNotEnd(entry) == TRUE);

   do {

      fgets(entry, 50, stdin);

      if (isNotEnd(entry) == TRUE) {
         // faz a pesquisa
        int encontrado = pesquisaBinaria(entry);

        if (encontrado == TRUE) 
            printf("SIM\n");
        else 
            printf("NAO\n");
      }

   } while (isNotEnd(entry) == TRUE);

   return 0;
}
