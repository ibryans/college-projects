/**
 * Pilha dinâmica de personagens
 */
class Pilha {
    Celula topo;

    Pilha() {
        topo = null;
    }

    /**
     * Empilha um personagem na pilha
     * @param p - Personagem
     */ 
    public void inserir(Personagem p) {
        Celula tmp = new Celula(p);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
    }

    /**
     * Desempilha um personagem
     * @return personagem removido
     * @throws Exception Quando a pilha está vazia
     */
    public Personagem remover() throws Exception {
        if (topo == null)
            throw new Exception("Erro! Pilha vazia");

        Celula removed = topo;
        Personagem resp = removed.elemento;
        topo = topo.prox;
        removed = null;

        return resp;
    }
 
    public void mostrar(Celula i, int count) {
        if (i != null) {
            mostrar(i.prox, count-1);
            System.out.print("[" + count  + "] ");
            i.elemento.imprimir();
        }
    }

    public void mostrar() {
        mostrar(topo, tamanho());
    }
 
    /**
     * Retorna o número de elementos da Pilha
     * @return
     */
    public int tamanho() {
        int t = 0;
        for(Celula i = topo; i.prox != null; i = i.prox, t++);
        return t;
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