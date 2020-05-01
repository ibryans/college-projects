class exercicios {

    Celula topo;


    // Questão 01
    int r_somarElementos() {
        return r_somarElementos(topo);
    }

    int r_somarElementos(Celula cel) {
        int soma = 0;
        if (cel != null)
            soma += cel.elemento + r_somarElementos(cel.prox);
        return soma;
    }



    // Questão 02
    int maiorElemento() throws Exception {
        if (topo == null)
            throw new Exception("Pilha vazia!");
        int maior = topo.elemento;
        for(Celula i = topo.prox; i != null; i = i.prox)
            if (i.elemento > maior)
                maior = i.elemento;
        return maior;
    }



    // Questão 03  
    int r_maiorElemento() throws Exception {
        if (topo == null)
            throw new Exception("Pilha vazia!");
        return r_maiorElemento(topo.prox, topo.elemento);
    }

    int r_maiorElemento(Celula index, int maior) {
        int maiorNum = maior;
        if (index != null) {
            if (index.elemento > maior)
                maiorNum = r_maiorElemento(index.prox, index.elemento);
            else
                maiorNum = r_maiorElemento(index.prox, maior);
        }
        return maiorNum;
    }


    // Questão 04
    void r_mostrarNaOrdemDeRemocao() throws Exception {
        if (topo == null) 
            throw new Exception("Pilha vazia!");
        System.out.println("Ordem de remoção: ");
        r_mostrarNaOrdemDeRemocao(topo);
    }

    void r_mostrarNaOrdemDeRemocao(Celula index) {
        if (index != null) {
            System.out.print(index.elemento + " ");
            r_mostrarNaOrdemDeRemocao(index.prox);
        }
    }
    


    // Questão 05 
    void r_mostrarNaOrdemDeInsercao() throws Exception {
        if (topo == null) 
            throw new Exception("Pilha vazia!");
        System.out.print("Ordem de inserção: ");
        r_mostrarNaOrdemDeInsercao(topo);
        System.out.println();
    }

    void r_mostrarNaOrdemDeInsercao(Celula index) {
        if (index != null) {
            r_mostrarNaOrdemDeInsercao(index.prox);
            System.out.print(index.elemento + " ");
        }
    }


    // Questão 06
    void mostrarNaOrdemDeInsercao() throws Exception {
        if (topo == null) 
            throw new Exception("Pilha vazia!");

        Celula ultimo;
        Celula aux;

        System.out.print("Ordem de inserção: ");

        // Indo com ultimo até o ultimo ponteiro (null)
        for(ultimo = topo; ultimo != null; ultimo = ultimo.prox);

        // Voltando com o ultimo pro topo, printando os elementos
        while (ultimo != topo) {
            for(aux = topo; aux.prox != ultimo; aux = aux.prox);
            System.out.print(aux.elemento + " ");
            ultimo = aux;
        }

        System.out.println();
    }
}

class Celula {
    int elemento;
    Celula prox;
}