import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tp04q02 {
    
    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        String entry = "", search = "";
        Personagem p;
        ArvoreArvore arvore = new ArvoreArvore();

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

        // Faz a pesquisa por nome
        do {
            search = MyIO.readLine();

            if (isNotEnd(search)) {
                arvore.pesquisar(search);
            }

        } while (isNotEnd(search));

    }
}

/** 
 * Classe de árvore Binária
 * @author Bryan Santos Oliveira
 */
class ArvoreArvore {

    NoPrimeiro raiz;

    ArvoreArvore() throws Exception {
        raiz = null;
        raiz = inserirChave(7, raiz);
        raiz = inserirChave(3, raiz);
        raiz = inserirChave(11, raiz);
        raiz = inserirChave(1, raiz);
        raiz = inserirChave(5, raiz);
        raiz = inserirChave(9, raiz);
        raiz = inserirChave(12, raiz);
        raiz = inserirChave(0, raiz);
        raiz = inserirChave(2, raiz);
        raiz = inserirChave(4, raiz);
        raiz = inserirChave(6, raiz);
        raiz = inserirChave(8, raiz);
        raiz = inserirChave(10, raiz);
        raiz = inserirChave(13, raiz);
        raiz = inserirChave(14, raiz);
    }

    /**
     * Método que insere um personagem na árvore de um determinado nó da primeira árvore
     * @param p Personagem (Nome)
     * @param no Nó da primeira árvore
     * @throws Exception
     */
    private NoSegundo inserirSegunda(String p, NoSegundo no) throws Exception {
        if (no == null)
            no = new NoSegundo(p);

        else if (p.compareTo(no.elemento) > 0)
            no.dir = inserirSegunda(p, no.dir);

        else if (p.compareTo(no.elemento) < 0)
            no.esq = inserirSegunda(p, no.esq);
        
        else
            throw new Exception("Erro! Personagem já existe na árvore");

        return no;
    }

    /**
     * Insere um personagem na 2ª árvore com a chave na 1ª árvore
     * @param p
     * @throws Exception
     */
    private NoPrimeiro inserir(Personagem p, NoPrimeiro no) throws Exception {
        
        if (no != null) {
            if (p.getAltura() % 15 == no.chave)
                no.arvore = inserirSegunda(p.getNome(), no.arvore);

            else if (p.getAltura() % 15 < no.chave)
                no.esq = inserir(p, no.esq);

            else if (p.getAltura() % 15 > no.chave)
                no.dir = inserir(p, no.dir);

            else 
                throw new Exception("Erro ao inserir!");
        }

        return no;
    }

    /** Método para inserir um personagem na estrutura */
    public void inserir(Personagem p) throws Exception {
        raiz = inserir(p, raiz);
    }

    /** Insere uma chave (nó) na primeira árvore */
    public NoPrimeiro inserirChave(int chave, NoPrimeiro no) throws Exception {
        if (no == null)
            no = new NoPrimeiro(chave);

        else if (chave < no.chave)
            no.esq = inserirChave(chave, no.esq);

        else if (chave > no.chave)
            no.dir = inserirChave(chave, no.dir);
        
        else
            throw new Exception("Erro! chave já existe na árvore");

        return no;
    }

    /**
     * Pesquisar um personagem na árvore de um nó da primeira árvore
     * @param nome Nome pesquisado
     * @param no Nó atual (segunda árvore)
     * @return <code>True</code> ou <code>False</code>
     */
    private boolean pesquisarSegunda(String nome, NoSegundo no) {
        boolean resp = false;

        if (no != null) {
            if (no.elemento.equals(nome))
                resp = true;

            if (!resp) {
                MyIO.print("ESQ ");
                resp = pesquisarSegunda(nome, no.esq);
            }

            if (!resp) {
                MyIO.print("DIR ");
                resp = pesquisarSegunda(nome, no.dir);
            }
        }

        return resp;
    }

    /**
     * Método privado que pesquisa por um personagem na árvore de árvore
     * @param nome
     * @param no
     * @return um Booleano indicando se tem ou não
     */
    private boolean pesquisar(String nome, NoPrimeiro no) {
        boolean resp = false;

        if (no != null) {
            resp = pesquisarSegunda(nome, no.arvore);

            // Não achou na árvore do nó atual
            if (!resp) {

                // Procura na esquerda
                MyIO.print("esq ");
                resp = pesquisar(nome, no.esq);
                
                // Se não achou na esquerda procura na direita
                if (!resp) {
                    MyIO.print("dir ");
                    resp = pesquisar(nome, no.dir);
                }
            }
        }

        return resp;
    }

    /**
     * Pesquisa por um personagem na árvore e printa SIM ou NAO
     * @param nome
     */
    public void pesquisar(String nome) {
        MyIO.print(nome + " raiz ");
        boolean result = pesquisar(nome, raiz);

        if (result) MyIO.println("SIM");
        else MyIO.println("NÃO");
    }

}

/** 
 * O nó da primeira árvore binária 
 */
class NoPrimeiro {
    NoPrimeiro esq, dir;
    NoSegundo arvore;
    int chave;

    NoPrimeiro() {
        this(0);
    }

    NoPrimeiro(int c) {
        this.esq = this.dir = null;
        this.arvore = null;
        this.chave = c;
    }
}

/**
 * O nó da segunda árvore binária
 */
class NoSegundo {
    NoSegundo esq, dir;
    String elemento;

    NoSegundo() {
        this("");
    }

    NoSegundo(String p) {
        elemento = p;
        esq = dir = null;
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
