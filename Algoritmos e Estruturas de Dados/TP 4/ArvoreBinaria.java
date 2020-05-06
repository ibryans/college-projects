import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/** 
 * Classe de árvore Binária
 * @author Bryan Santos Oliveira
 */
class ArvoreBinaria {

    No raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    /**
     * Insere um personagem na árvore com a chave de pesquisa sendo o nome
     * @param p
     * @throws Exception
     */
    public void inserir(Personagem p, No no) throws Exception {
        if (no == null)
            no = new No(p);
        else if (p.getNome().compareTo(no.elemento.getNome()) > 0)
            inserir(p, no.dir);
        else if (p.getNome().compareTo(no.elemento.getNome()) < 0)
            inserir(p, no.esq);
        else
            throw new Exception("Erro! Personagem já existe na árvore");
    }

    /**
     * Pesquisa por um personagem na árvore através do nome
     * @param nome
     * @param no
     * @return um Booleano indicando se tem ou não
     */
    public boolean pesquisar(String nome, No no) {
        boolean resp = false;
        if (no != null) {
            if (no.elemento.getNome().equals(nome))
                resp = true;
            else if (nome.compareTo(no.elemento.getNome()) > 0) {
                System.out.println("dir ");
                resp = pesquisar(nome, no.dir);
            }
            else {
                System.out.println("esq ");
                resp = pesquisar(nome, no.esq);
            }
        }
        return resp;
    }

}

/** 
 * O nó de uma árvore binária 
 */
class No {
    No esq, dir;
    Personagem elemento;

    No() {
        this(new Personagem());
    }

    No(Personagem p) {
        this.esq = this.dir = null;
        this.elemento = p;
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
