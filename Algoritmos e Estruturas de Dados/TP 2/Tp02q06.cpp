#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#define MAX 1000
#define LENGTH 6
#define TRUE 1
#define FALSE 0

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

void inserir(Personagem p);
Personagem remover();
int mediaDasAlturas();

/****************** PERSONAGEM ******************/

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


/************************** FILA CIRCULAR  *****************************/

Personagem fila[LENGTH];   // Elementos da fila 
int primeiro;          // Remove do indice "primeiro".
int ultimo;             // Insere no indice "ultimo".


/**
 * Inicializacoes
 */
void start(){
   primeiro = ultimo = 0;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param p Personagem a ser inserido.
 * @Se a fila estiver cheia.
 */
void inserir(Personagem p) {

    // se a fila estiver cheia, remove um pra depois inserir
    if (((ultimo + 1) % LENGTH) == primeiro) {
        // printf("\nteve que remover\n");
        remover();
    }

    fila[ultimo] = p;
    ultimo = (ultimo + 1) % LENGTH;

    printf("%d\n", mediaDasAlturas());
}


/**
 * Remove um elemento da primeira posicao da fila e movimenta 
 * os demais elementos para o primeiro da mesma.
 * @return resp Personagem a ser removido.
 * @Se a fila estiver vazia.
 */
Personagem  remover() {

   //validar remocao
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
      exit(1);
   }

   Personagem resp = fila[primeiro];
   primeiro = (primeiro + 1) % LENGTH;

   return resp;
}


/**
 * Mostra os fila separados por espacos.
 */

void mostrar (){
   int i;

   for(i = primeiro; i != ultimo; i = ((i + 1) % LENGTH)) {
      printf("[%d]", i);
      imprimir(fila[i]);
   }
}


/**
 * Retorna um bool indicando se a fila esta vazia
 * @return bool indicando se a fila esta vazia
 */
bool isVazia() {
   return (primeiro == ultimo); 
}

int mediaDasAlturas() {
    float media = 0;
    float tamanho = 0;
    for(int i = primeiro; i != ultimo; i = ((i + 1) % LENGTH)) {
        media += fila[i].altura;
        tamanho++;
    }
    media = round(media/tamanho);
    return media;
}

/**
* Lê uma lista de comandos da lista e os executa
*/
void commandsList() {
    int num;
    scanf("%d\n", &num);

    for(int i = 0; i < num; i++) {

        char comando;

        /* Ler os dados */
        scanf("%c", &comando);

        char entry[50];
        Personagem removido;
        Personagem adicionado;

        // Inserir
        if (comando == 'I') {
            getchar();
            fgets(entry, 50, stdin);
            adicionado = ler(entry);
            inserir(adicionado);
        }
        
        else if(comando == 'R') {
            removido = remover();
            printf("(R) %s\n", removido.nome);
            getchar();
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
         inserir(personagens[n]);
         n++;
      }

   } while (isNotEnd(entry) == TRUE);

   commandsList();

   mostrar();

   return 0;
}
