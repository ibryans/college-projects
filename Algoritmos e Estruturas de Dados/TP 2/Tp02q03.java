import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

class Tp02q03 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    /**
     * Lê uma lista de comandos da lista e os executa
     * 
     * @param lista   - A lista que está trabalhando
     * @return lista - A lista com os personagems inseridos/removidos
     */
    public static Lista commandsList(Lista lista) {

        int num = MyIO.readInt();

        for (int i = 0; i < num; i++) {

            try {

                String linha = MyIO.readLine();
                String[] commands = linha.split(" ");
                String c = commands[0];
                
                int pos;

                Personagem removido;
                Personagem personagem = new Personagem();

                switch (c) {
                case "II":
                    personagem.ler(commands[1]);
                    lista.inserirInicio(personagem);
                    break;

                case "IF":
                    personagem.ler(commands[1]);
                    lista.inserirFim(personagem);
                    break;

                case "I*":
                    pos = Integer.parseInt(commands[1]);
                    personagem.ler(commands[2]);
                    lista.inserir(personagem, pos);
                    break;

                case "RI":
                    removido = lista.removerInicio();
                    System.out.println("(R) " + removido.getNome());
                    break;

                case "RF":
                    removido = lista.removerFim();
                    System.out.println("(R) " + removido.getNome());
                    break;

                case "R*":
                    pos = Integer.parseInt(commands[1]);
                    removido = lista.remover(pos);
                    System.out.println("(R) " + removido.getNome());
                    break;

                default:
                    System.out.println("Outro");
                    break;
                }

            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        return lista;
    }

    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");

        Personagem personagem;
        Lista lista = new Lista(1000);
        
        String entry = "";
        do {
            entry = MyIO.readLine();

            personagem = new Personagem();

            if (isNotEnd(entry)) {
                try {
                    personagem.ler(entry);
                    lista.inserirFim(personagem);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } while (isNotEnd(entry));

        lista = commandsList(lista);

        lista.mostrar();

    }

}

/**
 * Lista sequencial de personagens
 */
class Lista {
    private Personagem[] personagens;
    private int n;
 
 
    /**
     * Construtor da classe.
     */
    public Lista () {
       this(6);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho Tamanho da lista.
     */
    public Lista (int tamanho){
       personagens = new Personagem[tamanho];
       n = 0;
    }
 
 
    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * @param p Personagem a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Personagem p) throws Exception {
 
       // Valida a insercao
       if(n >= this.personagens.length){
          throw new Exception("Erro ao inserir!");
       } 
 
       // Desloca os elementos para o fim do this.personagens
       for(int i = n; i > 0; i--){
          this.personagens[i] = this.personagens[i-1];
       }
 
       this.personagens[0] = p;
       n++;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da lista.
     * @param p Personagem a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Personagem p) throws Exception {
 
       //validar insercao
       if(n >= this.personagens.length){
          throw new Exception("Erro ao inserir!");
       }
 
       this.personagens[n] = p;
       n++;
    }
 
 
    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * @param p Personagem a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(Personagem p, int pos) throws Exception {
 
       //validar insercao
       if(n >= this.personagens.length || pos < 0 || pos > n){
          throw new Exception("Erro ao inserir!");
       }
 
       //levar elementos para o fim do this.personagens
       for(int i = n; i > pos; i--){
          this.personagens[i] = this.personagens[i-1];
       }
 
       this.personagens[pos] = p;
       n++;
    }
 
 
    /**
     * Remove um elemento da primeira posicao da lista e movimenta 
     * os demais elementos para o inicio da mesma.
     * @return resp Personagem a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerInicio() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagem resp = this.personagens[0];
       n--;
 
       for(int i = 0; i < n; i++){
          this.personagens[i] = this.personagens[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Remove um elemento da ultima posicao da lista.
     * @return resp Personagem a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerFim() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       return this.personagens[--n];
    }
 
 
    /**
     * Remove um elemento de uma posicao especifica da lista e 
     * movimenta os demais elementos para o inicio da mesma.
     * @param pos Posicao de remocao.
     * @return resp Personagem a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Personagem remover(int pos) throws Exception {
 
       //validar remocao
       if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
       }
 
       Personagem resp = this.personagens[pos];
       n--;
 
       for(int i = pos; i < n; i++){
          this.personagens[i] = this.personagens[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for(int i = 0; i < n; i++){
            System.out.print("[" + i + "]");
            this.personagens[i].imprimir();
        }
    }

    /**
     * Retorna o tamanho da lista (variavel n)
     * @return
     */
    public int tamanho() {
        return this.n;
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
        this("", 0, 0.0, "", "", "", "", "", "");
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