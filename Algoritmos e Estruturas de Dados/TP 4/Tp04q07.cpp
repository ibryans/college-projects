#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define maxValue(x,y) (x > y ? x : y)
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
    return (strlen(entry) >= 3 && (entry[0] == 'F' && entry[1] == 'I' && entry[2] == 'M'))
    ? FALSE
    : TRUE;
}

/********************** Tabela Hash indireta com lista ************************/

// Célula da lista
typedef struct Celula{
    Personagem personagem;
    Celula *prox;
} Celula;

// A 'classe' lista
typedef struct Lista{
    Celula *primeiro;
    Celula *ultimo;
} Lista;


/***** Construtores *****/

Celula* new_Celula_vazia(){
    Celula* nova = (Celula*) malloc (sizeof(Celula));
    nova->prox = NULL;
    return nova;
}

Celula* new_Celula(Personagem p) {
    Celula* nova = (Celula*) malloc (sizeof(Celula));
    nova->personagem = p;
    nova->prox = NULL;
    return nova;
}

Lista new_Lista(){
    Lista nova;
    nova.primeiro = nova.ultimo = new_Celula_vazia();
    return nova;
}


/******* Métodos de inserção e pesquisa ******/

/** Inserção no final de um personagem no final da lista **/
void inserirFim(Lista *lista, Personagem p){
    lista->ultimo->prox = new_Celula(p);
    lista->ultimo = lista->ultimo->prox;
}

/**
 * Pesquisa por um personagem na lista e retorna um booleano
 */
bool pesquisarLista(Lista lista, char* name){
    Celula *i = lista.primeiro;
    bool encontrado = false;

    while(!encontrado && i != NULL){
        if(strcmp(i->personagem.nome, name) == 0)
            encontrado = true;
        else
            i = i->prox;
    }

    return encontrado;
}
 
// Declaração da tabela
Lista* tabela;
const int tamTab = 25;

/** Função que inicia (construtor) da tabela hash **/
void start() {
    tabela = (Lista*) malloc (tamTab * sizeof(Lista));
    for(int i=0; i<tamTab; i++)
        tabela[i] = new_Lista();
}

/** Função de hash a partir de um personagem **/
int hash(Personagem p) {
    return (int)(p.altura % tamTab);
}

/** Faz a inserção de um personagem na tabela **/
void inserir (Personagem p) {
    int i = hash(p);
    inserirFim(&tabela[i], p);
}

/** Pesquisa por um personagem pelo nome **/
bool pesquisar(char *name) {
	bool encontrado = false;
    int i=0;
    while(!encontrado && i<tamTab){
        encontrado = pesquisarLista(tabela[i], name);
        i++;
    }

	return encontrado;
}


int main() {
    
    int n = 0;
    Personagem personagens[MAX];
    char entry[50], search[50];

    start();

    // Faz a inserção dos personagens na tabela
    do {
        fgets(entry, 50, stdin);

        if (isNotEnd(entry) == TRUE) {
            personagens[n] = ler(entry);
            inserir(personagens[n]);
            n++;
        }

    } while (isNotEnd(entry) == TRUE);

    // Pesquisa os personagens por nome
    do {
        fgets(search, 50, stdin);

        // retirando o '\n' do fim da string
        for (int i = 0; i < 50; i++)
            if( search[i] == '\n')
                search[i] = '\0';

        // Fazendo a pesquisa em si        
        if (isNotEnd(search) == TRUE) {
            printf("%s ", search);
            if (pesquisar(search))
                printf("SIM\n");
            else
                printf("NÃO\n");
        }

    } while (isNotEnd(search) == TRUE);

    return 0;
}