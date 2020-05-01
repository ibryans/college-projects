public class Tp03q05 {
    public static void main(String[] args) throws Exception {
       
        int n = MyIO.readInt();

        for (int i = 0; i < n; i++) {
            Matriz m1 = new Matriz(MyIO.readInt(), MyIO.readInt());
            m1.ler();

            Matriz m2 = new Matriz(MyIO.readInt(), MyIO.readInt());
            m2.ler();

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            Matriz soma = m1.soma(m2);
            soma.mostrar();

            Matriz multi = m1.multiplicacao(m2);
            multi.mostrar();
        }
    }
}

/**
 * Uma matriz dinâmica
 * @author Bryan Santos Oliveira
 */
class Matriz {

    int linhas, colunas;
    Celula inicio;

    /**
     * Aloca todos os elementos da matriz, baseado no número de linhas e colunas passados
     * @param linhas
     * @param colunas
     * @throws Exception - Se forem passados valores inválidos pra linha ou coluna
     */
    Matriz(int linhas, int colunas) throws Exception {

        if (linhas <= 0 || colunas <= 0) {
            throw new Exception("Erro! Valores inválidos para linhas ou colunas");
        }
        
        this.linhas = linhas;
        this.colunas = colunas;

        this.inicio = new Celula();

        int c, l;
        Celula i, j;

        // Alocando a primeira linha da matriz
        for(i = inicio, c = 1; c < this.colunas; c++, i = i.dir) {
            i.dir = new Celula();
            i.dir.esq = i;
        }

        // Alocando as demais linhas
        for (i = inicio, l = 1; l < this.linhas; l++, i = i.inf) {

            // Cria o primeiro elemento da próxima linha  
            i.inf = new Celula();
            i.inf.sup = i;

            // Termina a linha criando todas as suas colunas
            for (j = i.inf, c = 1; c < this.colunas; c++, j = j.dir) {
                j.dir = new Celula();
                j.dir.esq = j;
                j.dir.sup = j.sup.dir;
                j.dir.sup.inf = j.dir;
            }
        }

    }

    /**
     * Soma uma matriz com outra passada por parâmetro
     * @param other Segunda matriz
     * @return A matriz resultante
     * @throws Exception Se as matrizes forem de tamanhos diferentes
     */
    public Matriz soma(Matriz other) throws Exception {
        if (!(this.linhas == other.linhas && this.colunas == other.colunas))
            throw new Exception("Erro! Matrizes de tamanhos diferentes");

        Matriz result = new Matriz(this.linhas, this.colunas);

        for (Celula res = result.inicio, i = this.inicio, o = other.inicio; res != null; res = res.inf, i = i.inf, o = o.inf) {
            for (Celula jRes = res, jI = i, jO = o; jRes != null; jRes = jRes.dir, jI = jI.dir, jO = jO.dir) {
                jRes.elemento = jI.elemento + jO.elemento;
            }
        }

        return result;
    }

    /**
     * Multiplica uma matriz com outra passada por parâmetro
     * @param other Segunda matriz
     * @return Matriz resultante
     * @throws Exception Se o numero de linhas e colunas das duas forem incompativeis para a multiplicação
     */
    public Matriz multiplicacao(Matriz other) throws Exception {
        if (!(this.linhas == other.colunas))
            throw new Exception("Erro! Matrizes de tamanhos incompatíveis");

        Matriz result = new Matriz(this.linhas, other.colunas);
        int soma = 0;

        for (Celula i = this.inicio, res = result.inicio; i != null && res != null; i = i.inf, res = res.inf) {
            for (Celula b = other.inicio, colunaRes = res; b != null && colunaRes != null; b = b.dir, colunaRes = colunaRes.dir) {

                // Multiplicando os elementos da primeira com os da segunda
                for (Celula linhaI = i, linhaB = b; linhaI != null && linhaB != null; linhaI = linhaI.dir, linhaB = linhaB.inf) {
                    soma += linhaI.elemento * linhaB.elemento;
                }

                colunaRes.elemento = soma;
                soma = 0;
            }
        }

        return result;
    }


    /**
     * Mostra os elementos da diagonal principal da Matriz
     * @throws Exception quando a matriz está vazia
     */
    public void mostrarDiagonalPrincipal() throws Exception {
        if (linhas == 0 || colunas == 0)
            throw new Exception("Erro! Matriz vazia");

        if (linhas == colunas) {
            for (Celula i = inicio; i != null; i = i.dir) {
                System.out.print(i.elemento + " ");
                if (i.inf != null)
                    i = i.inf;
            }
        }

        System.out.println();
    }

    /**
     * Mostra os elementos da diagonal secundaria da Matriz
     * @throws Exception
     */
    public void mostrarDiagonalSecundaria() throws Exception {
        if (linhas == 0 || colunas == 0)
            throw new Exception("Erro! Matriz vazia");

        Celula i = inicio;

        while (i.dir != null) {
            i = i.dir;
        }

        // Printando os elementos da diagonal secundária
        while (i != null) {
            System.out.print(i.elemento + " ");
            i = (i.inf != null) ? i.inf.esq : null;
        }

        System.out.println();
    }

    /**
     * Lê e insere os elementos da matriz
     */
    public void ler() {
        Celula topo = inicio;
        Celula temp;

        for (int countLinha = 0; countLinha < linhas; countLinha++, topo = topo.inf) {
            temp = topo;
            String[] linha = MyIO.readLine().split(" ");
            for (int countColuna = 0; countColuna < colunas; countColuna++, temp = temp.dir) {
                temp.elemento = Integer.parseInt(linha[countColuna]);
            }
        }
    }

    public void mostrar(){
        for(Celula i = this.inicio; i != null; i = i.inf){
           for(Celula j = i; j != null; j = j.dir){
              System.out.print(j.elemento + " ");
           }
           System.out.println("");
        }
    }
}

/**
 * A célula de uma Matriz dinâmica
 */
class Celula {
    Celula sup, dir, inf, esq;
    int elemento;

    Celula() {
        this(0);
    }

    Celula(int x) {
        sup = dir = inf = esq = null;
        elemento = x;
    }
}