import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tp04q06 {
    
    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        String entry = "", search = "";
        Personagem p;
        HashRehash tabela = new HashRehash();

        MyIO.setCharset("ISO-8859-1");

        // Leitura dos dados pra inserir na tabela
        do {
            entry = MyIO.readLine();
            p = new Personagem();

            if (isNotEnd(entry)) {
                try {
                    p.ler(entry);
                    tabela.inserir(p);
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
                MyIO.print(search + " ");
                if(tabela.pesquisar(search))
                    MyIO.println("SIM");
                else
                    MyIO.println("NÃO");
            }

        } while (isNotEnd(search));

    }
}

/**
 * Classe de tabela hash com uso de rehash
 * @autho Bryan Santos Oliveira
 */
class HashRehash {
    public Personagem[] tabela;
    public int tamanho;

    HashRehash() {
        this(25);
    }
    
    /** 
     * Construtor da classe passando o tamanho da tabela
     */
    HashRehash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Personagem[(tamanho)]; 

        for (int i = 0; i < tabela.length; i++)
            tabela[i] = null;
    }

    /**
     * Função de hash a partir de um personagem
     * @param p Personagem
     * @return inteiro
     */
    public int hash(Personagem p) {
        return (p.getAltura() % this.tamanho);
    }
    
    /**
     * Função de rehash a partir de um personagem
     * @param p Personagem
     * @return inteiro
     */ 
    public int reHash(Personagem p) {
        return ((p.getAltura()+1) % this.tamanho);
    }

    /**
     * Inserção de um personagem na tabela
     * @param p
     */
    public void inserir(Personagem p) {
        int pos = hash(p);

        if (tabela[pos] == null) 
            tabela[pos] = p;

        else{
            pos = reHash(p);

            if (tabela[pos] == null) 
                tabela[pos] = p;
        }

    }

    /**
     * Pesquisa de um personagem pelo nome na tabela
     * @param nome Nome do personagem
     */
    public boolean pesquisar(String nome) {
        boolean encontrado = false;
        
        for (int i = 0; !encontrado && i < tabela.length; i++)
            encontrado = (tabela[i] == null) ? false : tabela[i].getNome().equals(nome);

        return encontrado;
    }

    
    public void mostrar(){
        for (int i = 0; i < tabela.length; i++)
            if (tabela[i] != null) tabela[i].imprimir();
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
