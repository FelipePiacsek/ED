package arvore;

import java.util.Stack;

/**
 * Representa uma árvore binária de busca. Contém operação de busca e inserção.
 * @author Felipe Piacsek
 */
public class ArvoreBinariaBusca {

    private ArvoreBinariaBusca esquerda;

    private ArvoreBinariaBusca direita;

    private Integer valor;

    public ArvoreBinariaBusca(Integer valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public ArvoreBinariaBusca getEsquerda() {
        return this.esquerda;
    }

    public ArvoreBinariaBusca getDireita() {
        return this.direita;
    }

    public Integer getValor() {
        return this.valor;
    }

    /**
     * Método que verifica se a árvore contém um dado conteúdo via busca eRd.
     * @param arvore
     * @return booleano que informa se dado inteiro está ou não presente na árvore.
     */
    public boolean contem(Integer c) {
        boolean encontrado = false;
        ArvoreBinariaBusca cursor = this;
        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!encontrado && !pilha.isEmpty()) {
                cursor = pilha.pop();
                cursor = cursor.getDireita();
            }
            while (!encontrado && cursor != null) {
                pilha.push(cursor);
                encontrado = cursor.getValor().equals(c);
                cursor = cursor.getEsquerda();
            }
        } while (!encontrado && !pilha.isEmpty());

        return encontrado;
    }

    /**
     * Insere um valor na árvore. Não é permitida a inserção de valores já presentes na árvore.
     * @param valor a ser inserido.
     * @return raiz da árvore.
     * @throws ValorPresenteException exceção lançada quando o valor a ser inserido já está na árvore.
     */
    public ArvoreBinariaBusca inserir(Integer valor) throws ValorPresenteException {
        ArvoreBinariaBusca anterior = this;
        ArvoreBinariaBusca cursor = this;
        while (cursor != null) {
            if (valor < cursor.valor) {
                anterior = cursor;
                cursor = cursor.esquerda;
            } else if (valor > cursor.valor) {
                anterior = cursor;
                cursor = cursor.direita;
            } else {
                throw new ValorPresenteException(valor);
            }
        }
        ArvoreBinariaBusca novoNo = new ArvoreBinariaBusca(valor);
        if (anterior.valor < valor) {
            anterior.direita = novoNo;
        } else {
            anterior.esquerda = novoNo;
        }
        return this;
    }

    /**
     * Remove um nó da árvore, mantendo as propriedades de uma Árvore Binária de Busca.
     * @param no nó a ser removido da árvore.
     * @return
     * @throws ArvoreNaoExistenteException Se o nó da árvore não estiver presente, lança exception.
     */
    public ArvoreBinariaBusca remover(ArvoreBinariaBusca no) throws ArvoreNaoExistenteException {

        boolean existe = this.equals(no);
        ArvoreBinariaBusca cursor = this;
        ArvoreBinariaBusca anteriorCursor = this;

        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!existe && !pilha.isEmpty()) {
                cursor = pilha.pop();
                anteriorCursor = cursor;
                cursor = cursor.getDireita();
            }
            while (!existe && cursor != null) {
                pilha.push(cursor);
                existe = cursor.equals(no);
                if (!existe) {
                    anteriorCursor = cursor;
                    cursor = cursor.getEsquerda();
                }
            }
        } while (!existe && !pilha.isEmpty());

        if (!existe) {
            throw new ArvoreNaoExistenteException(no.valor);
        }
        ArvoreBinariaBusca paiMaiorDentreMenores = obterPaiMaiorDentreMenores(cursor);
        ArvoreBinariaBusca maiorDentreMenores = paiMaiorDentreMenores.direita;
        paiMaiorDentreMenores.direita = maiorDentreMenores.esquerda;

        maiorDentreMenores.direita = cursor.direita;
        maiorDentreMenores.esquerda = cursor.esquerda;

        if (anteriorCursor.valor > maiorDentreMenores.valor) {
            anteriorCursor.esquerda = maiorDentreMenores;
        } else {
            anteriorCursor.direita = maiorDentreMenores;
        }

        return this;
    }

    private ArvoreBinariaBusca obterPaiMaiorDentreMenores(ArvoreBinariaBusca no) {
        ArvoreBinariaBusca cursor = no.esquerda;
        while (cursor != null && cursor.direita != null && cursor.direita.direita != null) {
            cursor = cursor.direita;
        }
        return cursor;
    }

    public String eRd() {
        ArvoreBinariaBusca cursor = this;
        StringBuilder erd = new StringBuilder(cursor.valor);
        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!pilha.isEmpty()) {
                cursor = pilha.pop();
                cursor = cursor.getDireita();
            }
            while (cursor != null) {
                pilha.push(cursor);
                cursor = cursor.getEsquerda();
            }
        } while (!pilha.isEmpty());
        return erd.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return ((ArvoreBinariaBusca) obj).valor == this.valor;
    }
}
