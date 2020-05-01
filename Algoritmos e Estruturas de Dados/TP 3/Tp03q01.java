import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Tp03q01 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    /**
     * Lê uma lista de comandos da lista e os executa
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
        Lista lista = new Lista();

        String entry = "";
        do {
            entry = MyIO.readLine();
            if (isNotEnd(entry)) {
                personagem = new Personagem();
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
 * Lista dinâmica de personagens
 */
class Lista {

    Celula primeiro, ultimo;

    Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um personagem no inicio da lista
     * @param p - Personagem
     */
    public void inserirInicio(Personagem p) {
        Celula novo = new Celula(p);
    
        novo.prox = primeiro.prox;
        primeiro.prox = novo;

        if (primeiro == ultimo) {
            ultimo = novo;
        }

        novo = null;
    }

    /**
     * Insere um personagem no final da lista
     * @param p - Personagem
     */ 
    public void inserirFim(Personagem p) {
        ultimo.prox = new Celula(p);
        ultimo = ultimo.prox;
    }

    /**
     * Insere um personagem em uma posição específica da lista
     * @param p - Personagem
     * @param index - posição
     * @throws Exception quando o index é inválido
     */
    public void inserir(Personagem p, int index) throws Exception {
        if (index < 0 || index > tamanho())
            throw new Exception("Erro! Posição inválida");

        if (index == 0)
            inserirInicio(p);

        else if (index == tamanho())
            inserirFim(p);

        else {
            Celula tmp = primeiro;
            for (int i = 0; i < index; i++, tmp = tmp.prox);

            Celula novo = new Celula(p);
            novo.prox = tmp.prox;
            tmp.prox = novo;

            tmp = novo = null;
        }
    }

    /**
     * Remove um personagem do inicio da lista
     * @return personagem removido
     * @throws Exception quando a lista está vazia
     */
    public Personagem removerInicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro! Lista vazia");

        Celula temp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.elemento;

        temp.prox = null;
        return resp;
    }

    /**
     * Remove um personagem do final da lista
     * @return personagem removido
     * @throws Exception quando a lista está vazia
     */
    public Personagem removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro! Lista vazia");

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);

        Personagem resp = ultimo.elemento;
        ultimo = i;

        ultimo.prox = null;
        i = null;
        return resp;
    }

    /**
     * Remove um personagem em uma posição específica
     * @param index - posição
     * @return Personagem removido
     * @throws Exception quando a lista ta vazia ou posição é inválida
     */
    public Personagem remover(int index) throws Exception {

        Personagem resp;

        if (primeiro == ultimo)
            throw new Exception("Erro ao remover (vazia)!");

        if (index < 0 || index >= tamanho())
            throw new Exception("Erro! Posição inválida");

        else if (index == 0)
            resp = removerInicio();

        else if (index == tamanho() - 1)
            resp = removerFim();

        else {
            Celula tmp = primeiro;
            for(int i = 0; i < index; i++, tmp = tmp.prox);
            
            Celula removido = tmp.prox;
            resp = removido.elemento;
            tmp.prox = removido.prox;
            removido.prox = null;
            tmp = removido = null;
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista (atraves do metodo imprimir de Personagem)
     */
    public void mostrar() {
        int c = 0;
        for(Celula i = primeiro.prox; i != null; i = i.prox, c++){
            System.out.print("[" + c + "] ");
            i.elemento.imprimir();
        }
    }
    /**
     * Retorna o tamanho da lista (length)
     * @return tamanho
     */ 
    public int tamanho() {
        int resp = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, resp++);
        return resp;
    }

}

class Celula {
    Celula prox;
    Personagem elemento;

    Celula() {
        this(new Personagem());
    }

    Celula(Personagem p) {
        elemento = p;
        prox = null;
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