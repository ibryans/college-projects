import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Q1 {
    static long tempoExecucao;
    static int numComparacoes = 0;
    static int numMovimentacoes = 0;
    static int numEntrada = 0;
    Personagem array[];
    static Lista lista = new Lista();
    Lista remocao[];

    public static void main(String[] args) throws Exception {

        // lista = new Lista();

        long tempoInicial = System.nanoTime();
        // MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        String[] segundaEntrada = new String[1000];
        int numEntradaLista = 0;

        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        numEntradaLista = MyIO.readInt();

        for (int i = 0; i < numEntradaLista; i++) {
            segundaEntrada[numEntradaLista] = MyIO.readLine();
        }

        Personagem[] personagens = new Personagem[numEntrada + numEntradaLista];

        for (int i = 0; i < numEntrada; i++) {
            personagens[i] = ler(entrada[i]);
            lista.inserirFim(personagens[i]);
        }
        
        //separarInsercao(segundaEntrada);


        // personagens = countingsort(personagens);
        // personagens = desempate(personagens);



        for(int i=0; i<numEntrada; i++){
            personagens[i].imprimir();
        }

        long tempoFinal = System.nanoTime();

        tempoExecucao = tempoFinal - tempoInicial;
        geraLog(tempoExecucao);

    }

    public static Personagem ler(String array){
        
        File f = new File(array);
        
        String auxString = "";
        String linha = "";

        try {
            FileReader arq = new FileReader(f); 
            BufferedReader lerArq = new BufferedReader(arq);

            auxString = lerArq.readLine();
            
            while(auxString != null){
                if(auxString!=null){
                
                    linha += auxString;

                }
                linha = linha.replace("{'", "");
                linha = linha.replaceAll("'}", "");
                linha = linha.replaceAll("': '", "#");
                linha = linha.replaceAll("', '", "#");
    
                auxString = lerArq.readLine();
            }
            arq.close();
        }catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());    
        }
        Personagem personagem = new Personagem();
        
        personagem.separarNome(linha);
        personagem.separarAltura(linha);
        personagem.separarPeso(linha);
        personagem.separarCorDoCabelo(linha);
        personagem.separarCorDaPele(linha);
        personagem.separarCorDosOlhos(linha);
        personagem.separarAnoNascimento(linha);
        personagem.separarGenero(linha);
        personagem.separarHomeworld(linha);
        //personagem.imprimir();

        return personagem;
        
    }

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }


    public static Personagem[] desempate(Personagem[] array){
        for(int i =0; i<numEntrada-1; i++){
            if(array[i].getPeso()==array[i+1].getPeso()){
                int inicio = i;
                numComparacoes++;
                while(i<(numEntrada-1) && array[inicio].getPeso()==array[i+1].getPeso()){
                    i++;
                    numComparacoes++;
                }
                int fim = i+1;

                
                for(; inicio < fim-1; inicio++){
    
                    int menor = inicio;
                    for (int j=(inicio+1); j < fim; j++){
                        if (array[menor].getNome().compareTo(array[j].getNome()) > 0){
                            menor = j;
                        } 
                        numComparacoes++;
                    }
            
                    Personagem temp = array[menor];
                    array[menor] = array[inicio];
                    numMovimentacoes++;
                    array[inicio] = temp;
                    numMovimentacoes++;
                }
            }
        }
        return array;
    }

    public static void separarInsercao(String x[]) throws Exception{
        String[] auxiliar;
        Personagem p;
        for(int i = 0; i<x.length; i++){
            if(x[i].charAt(0)=='I' && x[i].charAt(1)=='I'){
                auxiliar = x[i].split(" ");
                p = ler(auxiliar[1]);
                lista.inserirInicio(p);
            }
        
            if(x[i].charAt(0)=='I' && x[i].charAt(1)=='F'){
                auxiliar = x[i].split(" ");
                p = ler(auxiliar[1]);
                lista.inserirFim(p);
            }
        
            if(x[i].charAt(0)=='I' && x[i].charAt(1)=='*'){
                int pos=0;
                auxiliar = x[i].split(" ");
                pos = Integer.parseInt(auxiliar[1]);
                p = ler(auxiliar[2]);
                lista.inserir(p, pos);
               
            }
        
            if(x[i].charAt(0)== 'R' && x[i].charAt(1) == 'I'){
                lista.removerInicio(); 
                
            }
        
            if(x[i].charAt(0)=='R' && x[i].charAt(1) == 'F'){
                lista.removerFim(); 
                
            }
        
            if(x[i].charAt(0) == 'R' && x[i].charAt(1) == '*'){
                int pos=0;
                auxiliar = x[i].split(" ");
                pos = Integer.parseInt(auxiliar[1]);
        
                lista.remover(pos); 
            }
        }
    }


    static class Celula {
        public Personagem elemento; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox.
        
        public Celula() {
            this.elemento = new Personagem();
            this.prox = null;
        }
    
        public Celula(Personagem elemento) {
          this.elemento = elemento;
          this.prox = null;
        }
    }


    public static class Lista {
        private Celula primeiro;
        private Celula ultimo;
    
        
        /**
         * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
         */
        public Lista() {
            primeiro = new Celula();
            ultimo = primeiro;
        }
    
    
        public void inserirInicio(Personagem x) {
            Celula tmp = new Celula(x);
          tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            if (primeiro == ultimo) {                 
                ultimo = tmp;
            }
          tmp = null;
        }
    
        public void inserirFim(Personagem x) {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }
    
    
        /**
         * Remove um elemento da primeira posicao da lista.
        * @return resp int elemento a ser removido.
         * @throws Exception Se a lista nao contiver elementos.
         */
        public Personagem removerInicio() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            }
    
            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            Personagem resp = primeiro.elemento;
            tmp.prox = null;
            tmp = null;

            return resp;
        }
    
    
        /**
         * Remove um elemento da ultima posicao da lista.
        * @return resp int elemento a ser removido.
         * @throws Exception Se a lista nao contiver elementos.
         */
        public Personagem removerFim() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover (vazia)!");
            } 
    
            // Caminhar ate a penultima celula:
            Celula i;
            for(i = primeiro; i.prox != ultimo; i = i.prox);
        
            Personagem resp = ultimo.elemento; 
            ultimo = i; 
            i = ultimo.prox = null;
          
            return resp;
        }
    
    
       public void inserir(Personagem x, int pos) throws Exception {
    
          int tamanho = tamanho();
    
          if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
          } else if (pos == 0){
             inserirInicio(x);
          } else if (pos == tamanho){
             inserirFim(x);
          } else {
               // Caminhar ate a posicao anterior a insercao
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = new Celula(x);
             tmp.prox = i.prox;
             i.prox = tmp;
             tmp = i = null;
          }
       }
    

        public Personagem remover(int pos) throws Exception {
          Personagem resp;
          int tamanho = tamanho();
    
            if (primeiro == ultimo){
                throw new Exception("Erro ao remover (vazia)!");
    
          } else if(pos < 0 || pos >= tamanho){
                throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
          } else if (pos == 0){
             resp = removerInicio();
          } else if (pos == tamanho - 1){
             resp = removerFim();
          } else {
               // Caminhar ate a posicao anterior a insercao
             Celula i = primeiro;
             for(int j = 0; j < pos; j++, i = i.prox);
            
             Celula tmp = i.prox;
             resp = tmp.elemento;
             i.prox = tmp.prox;
             tmp.prox = null;
             i = tmp = null;
          }
    
            return resp;
        }
    
        /**
         * Mostra os elementos da lista separados por espacos.
         */
        public void mostrar() {
            
            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                i.elemento.imprimir();
            }
            
        }

        public int tamanho() {
            int tamanho = 0; 
            for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
            return tamanho;
         }
    

    }


    public static void geraLog(long tempoExecucao) throws IOException {
        FileWriter fw = new FileWriter(new File("650468_countingsort.txt"));
        fw.write("650468\t" + numComparacoes + "\t" + numMovimentacoes + "\t" + tempoExecucao);
        fw.close();
    }



    public static Personagem[] swap(Personagem[] array, int i, int j) {
        Personagem temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

}







class Personagem{
    private String nome, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld;
    private int altura;
    private double peso;

    public Personagem(){
        nome = "";
        corDoCabelo = "";
        corDaPele = "";
        corDosOlhos = "";
        anoNascimento = "";
        genero = "";
        homeworld = "";
        altura = 0;
        peso = 0;
    }
    public Personagem(String nome, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeworld ){
        this.nome = nome;
        this.corDoCabelo = corDoCabelo;
        this.corDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
        
    }

    public void imprimir(){
        MyIO.println(" ## " + getNome() + " ## " + getAltura() + " ## " + printarPeso() + " ## " + getCorDoCabelo() + " ## " + getCorDaPele() + " ## " + getCorDosOlhos() + " ## " + getAnoNascimento() + " ## " + getGenero() + " ## " + getHomeworld() + " ## ");
    }

    public Personagem clone(){
        return new Personagem(this.nome, this.corDoCabelo, this.corDaPele, this.corDosOlhos, this.anoNascimento, this.genero, this.homeworld);
    }

    public void separarNome(String linha){
        int i = 0;
        String nome = "";
        while(!nome.equals("name") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                nome="";
                i++;
            }
            else{
                nome += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            nome="";
            do{
                nome += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setNome(nome);
        
    }

    public void separarAltura(String linha){
        int i = 0;
        String auxString = "";
        int altura=0;

        while(!auxString.equals("height") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                auxString="";
                i++;
            }
            else{
                auxString += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            auxString="";
            do{
                auxString += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
            

            if(!auxString.equals("unknown")){
                
            altura = Integer.parseInt(auxString);
            setAltura(altura);
            }
            
            
        
        
    }

    public void separarPeso(String linha){
        int i = 0;
        String auxString = "";
        double peso=0;
        while(!auxString.equals("mass") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                auxString="";
                i++;
            }
            else{
                auxString += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            auxString="";
            do{
                auxString += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
            if(!auxString.equals("unknown")){
            auxString= auxString.replaceAll(",", "");
            peso = Double.parseDouble(auxString);
            setPeso(peso);
            }
            //MyIO.println(altura);
            //MyIO.println(auxString);
        
        
        //MyIO.print(getPeso() + " ## ");
    }

    public String printarPeso(){
        double peso = getPeso();
        String stringPeso = "";

        stringPeso = Double.toString(peso);
        //MyIO.println(stringPeso);
        stringPeso = stringPeso.replace(".0", "");

        //MyIO.print( stringPeso );
        return stringPeso;
    }

    public void separarCorDoCabelo(String linha){
        int i = 0;
        String corDoCabelo = "";
        while(!corDoCabelo.equals("hair_color") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                corDoCabelo="";
                i++;
            }
            else{
                corDoCabelo += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            corDoCabelo="";
            do{
                corDoCabelo += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setCorDoCabelo(corDoCabelo);
        
    }

    public void separarCorDaPele(String linha){
        int i = 0;
        String corDaPele = "";
        while(!corDaPele.equals("skin_color") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                corDaPele="";
                i++;
            }
            else{
                corDaPele += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            corDaPele="";
            do{
                corDaPele += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setCorDaPele(corDaPele);
        
    }

    public void separarCorDosOlhos(String linha){
        int i = 0;
        String corDosOlhos = "";
        while(!corDosOlhos.equals("eye_color") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                corDosOlhos="";
                i++;
            }
            else{
                corDosOlhos += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            corDosOlhos="";
            do{
                corDosOlhos += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setCorDosOlhos(corDosOlhos);
        
    }


    public void separarAnoNascimento(String linha){
        int i = 0;
        String anoNascimento = "";
        while(!anoNascimento.equals("birth_year") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                anoNascimento="";
                i++;
            }
            else{
                anoNascimento += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            anoNascimento="";
            do{
                anoNascimento += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setAnoNascimento(anoNascimento);
        
    }

    public void separarGenero(String linha){
        int i = 0;
        String genero = "";
        while(!genero.equals("gender") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                genero="";
                i++;
            }
            else{
                genero += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            genero="";
            do{
                genero += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setGenero(genero);
        
    }

    public void separarHomeworld(String linha){
        int i = 0;
        String homeworld = "";
        while(!homeworld.equals("homeworld") && i<linha.length()){
            if(linha.charAt(i)=='#'){
                homeworld="";
                i++;
            }
            else{
                homeworld += linha.charAt(i);
                i++;
            }
        }
            while(linha.charAt(i)=='#'){
                i++;
            }
            homeworld="";
            do{
                homeworld += linha.charAt(i);
                i++;
        
            }while(linha.charAt(i)!='#');
        setHomeworld(homeworld);
        
    }
    /**
     * set de todos os atributos da classe Personagem
     * 
     */

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setAltura(int altura) {
        this.altura = altura;
    }


    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }


    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }
    

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }


    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }
     

    
    /**
     * get de todos os atributos da classe Personagens
     * 
     */


     public String getNome(){
         return nome;
     }

     public String getCorDoCabelo(){
         return corDoCabelo;
     }

     public String getCorDaPele(){
         return corDaPele;
     }

     public String getCorDosOlhos(){
         return corDosOlhos;
     }

     public String getAnoNascimento(){
         return anoNascimento;
     }

     public int getAltura(){
         return altura;
     }

     public double getPeso(){
         return peso;
     }

     public String getGenero(){
         return genero;
     }

     public String getHomeworld(){
         return homeworld;
     }
}