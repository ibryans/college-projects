import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Tp04q04 {
    
    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        String entry = "", search = "";
        Personagem p;
        Alvinegra arvore = new Alvinegra();

        MyIO.setCharset("ISO-8859-1");

        // Leitura dos dados pra inserir na árvore
        do {
            entry = MyIO.readLine();
            p = new Personagem();

            if (isNotEnd(entry)) {
                try {
                    p.ler(entry);
                    arvore.inserir(p);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }

        } while (isNotEnd(entry));


        // Pesquisa por nome
        do {
            search = MyIO.readLine();

            if (isNotEnd(search)) {
                if(arvore.pesquisar(search))
                    System.out.println("SIM");
                else
                    System.out.println("NÃO");
            }

        } while (isNotEnd(search));

    }
}

/**
 * Arvore binária alvinegra de Personagens
 */
class Alvinegra {
    private No raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public Alvinegra() {
        raiz = null;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
        MyIO.print(elemento + " raiz ");
        return pesquisar(elemento, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * @param elemento Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String elemento, No i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (elemento.compareTo(i.elemento.getNome()) == 0) {
            resp = true;

        } else if (elemento.compareTo(i.elemento.getNome()) > 0) {
            MyIO.print("dir ");
            resp = pesquisar(elemento, i.dir);

        } else {
            MyIO.print("esq ");
            resp = pesquisar(elemento, i.esq);
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void mostrarCentral() {
        System.out.print("[ ");
        mostrarCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * @param i No em analise.
     */
    private void mostrarCentral(No i) {
        if (i != null) {
            mostrarCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
            mostrarCentral(i.dir); // Elementos da direita.
        }
    }


    /**
     * Metodo publico iterativo para inserir elemento.
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Personagem elemento) throws Exception {

        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(elemento, false);

        // Senao, se a arvore tiver um elemento 
        } else if (raiz.esq == null && raiz.dir == null){
            
            if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0)
                raiz.esq = new No(elemento, true);

            else
                raiz.dir = new No(elemento, true);

        // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){

            if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0)
                raiz.esq = new No(elemento);

            else if (raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;

        // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){

            if(raiz.elemento.getNome().compareTo(elemento.getNome()) < 0)
                raiz.dir = new No(elemento);
                
            else if (raiz.esq.elemento.getNome().compareTo(elemento.getNome()) < 0) {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;

        // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(No bisavo, No avo, No pai, No i){

        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){

            //4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null){
                raiz = avo;
            } else {
                if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) > 0){
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
            }

            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * @param elemento Elemento a ser inserido.
     * @param avo No em analise.
     * @param pai No em analise.
     * @param i No em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Personagem elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {
            if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0)
                i = pai.esq = new No(elemento, true);
            
            else
                i = pai.dir = new No(elemento, true);
            
            if (pai.cor == true)
                balancear(bisavo, avo, pai, i);
            
        } else {
            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz)
                    i.cor = false;
                else if(pai.cor == true)
                    balancear(bisavo, avo, pai, i);
            }

            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}


/**
 * Nó de uma árvore binária alvinegra de personagens
 * @author Bryan Santos Oliveira
 */
class No {
    public boolean cor;
    public Personagem elemento;
    public No esq, dir;

    public No() {
        this(new Personagem());
    }

    public No (Personagem elemento){
        this(elemento, false, null, null);
    }

    public No (Personagem elemento, boolean cor){
        this(elemento, cor, null, null);
    }

    public No (Personagem elemento, boolean cor, No esq, No dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

/**
 * Classe de um personagem de star wars, relacionado à API 'SWAPI'
 */
class Personagem {

    /*********** Atributos ***********/

    private String nome, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld;

    private int altura;

    private double peso;

    /********* Construtores *********/

    public Personagem() {
        this("");
    }

    public Personagem(String nome) {
        this(nome, 0, 0.0, "", "", "", "", "", "");
    }

    public Personagem(String nome, int altura, double peso, String cabelo, String pele, String olhos, String nascimento,
            String genero, String homeworld) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = cabelo;
        this.corDaPele = pele;
        this.corDosOlhos = olhos;
        this.anoNascimento = nascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    /******** Getters and setters ********/

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorDoCabelo() {
        return this.corDoCabelo;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDaPele() {
        return this.corDaPele;
    }

    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }

    public String getCorDosOlhos() {
        return this.corDosOlhos;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }

    public String getAnoNascimento() {
        return this.anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHomeworld() {
        return this.homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    /**** Métodos da classe *****/

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String pegarAtributo(String json, String atributo) throws Exception {

        boolean achado = false;
        String valor = "";
        int i = 0;

        // Vai até o atributo escolhido
        while (!achado && i < json.length()) {

            // Verifica se é aspas simples (39 = ')
            if (json.charAt(i) == 39) {

                i++;
                String nome = "";

                // Percorrer o conteudo dentro das aspas simples e salva em nome
                while (i < json.length() && json.charAt(i) != 39) {
                    nome += json.charAt(i);
                    i++;
                } 

                // Se o nome achado for o atributo escolhido
                if (nome.equals(atributo)) {
                    achado = true;
                }

            } else {
                i++;
            }
        }

        // Se saiu do while e não achou o atributo
        if (!achado) {throw new Exception("Atributo não encontrado!");}

        // Vai pro começo do valor do atributo
        i += 4;

        while (i < json.length() && json.charAt(i) != 39) {
            valor += json.charAt(i);
            i++;
        }

        return valor;
    }

    public void ler(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String json = reader.readLine();

        // Salvando os atributos

        this.nome = this.pegarAtributo(json, "name");

        String altura = this.pegarAtributo(json, "height");
        this.altura = (altura.equals("unknown")) ? 0 : Integer.parseInt(altura);

        String peso = this.pegarAtributo(json, "mass");
        if (peso.equals("unknown")) {
            this.peso = 0;
        }
        else { 
            this.peso = (peso.split(",").length > 1)
            ? Double.parseDouble(peso.split(",")[0] + peso.split(",")[1])
            : Double.parseDouble(peso.split(",")[0]); 
        }

        this.corDoCabelo = this.pegarAtributo(json, "hair_color");
        this.corDaPele = this.pegarAtributo(json, "skin_color");
        this.corDosOlhos = this.pegarAtributo(json, "eye_color");
        this.anoNascimento = this.pegarAtributo(json, "birth_year");
        this.genero = this.pegarAtributo(json, "gender");
        this.homeworld = this.pegarAtributo(json, "homeworld");

        reader.close();
    }

    public void imprimir() {
        MyIO.print(" ## " + this.nome);
        MyIO.print(" ## " + this.altura);
        MyIO.print(" ## " + String.valueOf(this.peso).replace(".0", ""));
        MyIO.print(" ## " + this.corDoCabelo);
        MyIO.print(" ## " + this.corDaPele);
        MyIO.print(" ## " + this.corDosOlhos);
        MyIO.print(" ## " + this.anoNascimento);
        MyIO.print(" ## " + this.genero);
        MyIO.println(" ## " + this.homeworld + " ##");
    }

}
