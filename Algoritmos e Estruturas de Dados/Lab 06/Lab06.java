import java.util.Scanner;

class Lab06 {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        while (entrada.hasNextInt()) {
            ListaTelefonica lista = new ListaTelefonica();
            int n = entrada.nextInt();

            for (int i = 0; i < n; i++) {
                lista.inserir(
                    Integer.toString(entrada.nextInt())
                );
            }

            lista.ordenar();
            System.out.println(lista.economizaCaracteres());
        }

        entrada.close();
    }
}

/**
 * Uma simples lista telefônica com tamanho flexível
 */
class ListaTelefonica {

    Celula primeiro;
    Celula ultimo;

    public ListaTelefonica() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um telefone no fim da lista
     */
    public void inserir(String tel) {
        ultimo.prox = new Celula(tel);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.telefone);
        }
    }

    /**
     * Ordena a lista de telefones por comparação de strings, utilizando o algoritmo de seleção (adaptado para Celula e não array)
     */
    public void ordenar() {
        for (Celula i = primeiro.prox; i != null ; i = i.prox) {
            Celula menor = i;
            for (Celula j = i.prox; j != null; j = j.prox) {
                if (j.telefone.compareTo(menor.telefone) < 0)
                    menor = j;
            }
            swap(menor, i);
        }
    }

    /**
     * Verifica os prefixos iguais a cada 2 telefones consecutivos para "omití-los", e assim, economizar tinta da impressora
     * @return Número de caracteres (ou números) que foram "omitidos"
     */
    public int economizaCaracteres() {
        int economizados = 0;
        for (Celula i = primeiro.prox.prox; i != null; i = i.prox) {
            Celula j = i.ant;
            for (int c = 0; c < i.telefone.length(); c++) {
                if (i.telefone.charAt(c) == j.telefone.charAt(c))
                    economizados++;
                else
                    c = i.telefone.length();
            }
        }
        return economizados;
    }

    public void swap(Celula a, Celula b) {
        String tmp = a.telefone;
        a.telefone = b.telefone;
        b.telefone = tmp;
    }

}

/**
 * Celula que contém o número de telefone armazenado. Tem dois ponteiros para acessar o telefone anterior e posterior à ele
 */
class Celula {
    Celula ant, prox;
    String telefone;

    Celula() {
        this("");
    }

    Celula(String telefone) {
        this.ant = this.prox = null;
        this.telefone = telefone;
    }
}