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

/********************** Árvore AVL ************************/

typedef struct No {
    Personagem elemento;
    No *esq, *dir;
    int nivel;
} No;
 

/** Construtor do Nó **/
No* new_No(Personagem p) {
   	No* novo = (No*) malloc (sizeof(No));
   	novo->elemento = p;
 	novo->esq = NULL;
   	novo->dir = NULL;
   	novo->nivel = 0;
   	return novo;
}

/** Raiz da árvore **/
No *raiz;

/** Construtor da árvore **/ 
void start() {
   raiz = NULL;
}

int getNivel(No *no) {
    return (no == NULL) ? 0 : no->nivel;
}

void setNivel(No *no) {
    no->nivel = 1 + maxValue(getNivel(no->esq), getNivel(no->dir));
}

/** Pesquisa recursivamente um personagem na árvore **/
bool r_pesquisar(char *nome, No *no) {
    bool resp;

    if (no == NULL) {
        resp = false;
 
    } else if (strcmp(nome, no->elemento.nome) == 0) {
        resp = true;
 
    } else if (strcmp(nome, no->elemento.nome) < 0) {
        printf("esq ");
        resp = r_pesquisar(nome, no->esq);
 
    } else {
        printf("dir ");
        resp = r_pesquisar(nome, no->dir);
    }
    
    return resp;
}

/** Método da árvore para pesquisar um personagem pelo nome **/
bool pesquisar(char *nome){
    printf("%s raiz ", nome);
    return r_pesquisar(nome, raiz);
}

/** Faz a rotação para a direita no nó para o balanceamento **/
No* rotacionarDir(No *no){
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;
 
    noEsq->dir = no;
    no->esq = noEsqDir;
 
    setNivel(no);
    setNivel(noEsq); 

    return noEsq;    
}

/** Faz a rotação para a esquerda no nó para o balanceamento **/
No* rotacionarEsq(No *no){
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;
 
    noDir->esq = no;
    no->dir = noDirEsq;
 
    setNivel(no);
    setNivel(noDir);    

    return noDir;
}

/** Método da árvore para o balanceamento **/
No* balancear(No *no){
    if(no != NULL){
    	int fator = ( getNivel(no->dir) - getNivel(no->esq) );
 
        // Se estiver balanceada
        if (abs(fator) <= 1)
        	setNivel(no);
 
        // Se estiver desbalanceada para a direita
        else if (fator == 2){
 
        	int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);
 
            // Se o filho da direita também estiver desbalanceado
            if (fatorFilhoDir == -1) {
                no->dir = rotacionarDir(no->dir);
            }

            no = rotacionarEsq(no);
 
        // Se estiver desbalanceada para a esquerda
        }else if (fator == -2){
 
        	int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);
 
            // Se o filho da esquerda também estiver desbalanceado
            if (fatorFilhoEsq == 1) {
                no->esq = rotacionarEsq(no->esq);
            }

            no = rotacionarDir(no);
 
        } else printf("Erro!");        
    }
    return no;
}

/** Faz a inserção de um personagem recursivamente **/
No* r_inserir(Personagem p, No* no) {
    if (no == NULL)
        no = new_No(p);
 
    else if(strcmp(p.nome, no->elemento.nome ) < 0)
    	no->esq = r_inserir(p, (no->esq));
 
    else if (strcmp(p.nome, no->elemento.nome ) > 0)
    	no->dir = r_inserir(p, (no->dir));
 
    else
        printf("Erro ao inserir!");

    /** Faz o balanceamento do nó após a inserção **/
    no = balancear(no);

    return no;
}

/** Método da árvore para a inserção de um personagem **/
void inserir(Personagem per) {
   raiz = r_inserir(per, raiz);
}


int main() {
    
    int n = 0;
    Personagem personagens[MAX];
    char entry[50], search[50];

    start();

    // Faz a inserção dos personagens na árvore
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
            if (pesquisar(search))
                printf("SIM\n");
            else
                printf("NÃO\n");
        }

    } while (isNotEnd(search) == TRUE);

    return 0;
}
