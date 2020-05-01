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