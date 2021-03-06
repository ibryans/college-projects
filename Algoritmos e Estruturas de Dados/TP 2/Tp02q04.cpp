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
* Lê uma lista de comandos da lista e os executa
*/
void commandsList() {
   int num;
   scanf("%d\n", &num);

   for(int i = 0; i < num; i++) {

      char comando1;
      char comando2;

      /* Ler os dados */
      scanf("%c %c", &comando1, &comando2);

      char entry[50];
      Personagem removido;
      Personagem adicionado;

      // Inserir
      if (comando1 == 'I') {

         // Início
         if (comando2 == 'I') {
            getchar();
            fgets(entry, 50, stdin);
            adicionado = ler(entry);
            inserirInicio(adicionado);
         }

         // Fim
         else if (comando2 == 'F') {
            getchar();
            fgets(entry, 50, stdin);
            adicionado = ler(entry);
            inserirFim(adicionado);
         }

         // Em uma posição específica
         else if (comando2 == '*') {
            int pos;
            scanf("%d", &pos);
            getchar();
            fgets(entry, 50, stdin);
            adicionado = ler(entry);
            inserir(adicionado, pos);
         }
      }
      
      else if(comando1 == 'R') {

         // Início
         if (comando2 == 'I') {
            removido = removerInicio();
            printf("(R) %s\n", removido.nome);
            getchar();
         }

         // Fim
         else if (comando2 == 'F') {
            removido = removerFim();
            printf("(R) %s\n", removido.nome);
            getchar();
         }

         // Em uma posição específica
         else if (comando2 == '*') {
            int pos;
            scanf("%d", &pos);
            removido = remover(pos);
            printf("(R) %s\n", removido.nome);
            getchar();
         }
      }

      else {
         printf("Comando inválido!\n");
      }
   }
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

   commandsList();

   mostrar();

   return 0;
}
