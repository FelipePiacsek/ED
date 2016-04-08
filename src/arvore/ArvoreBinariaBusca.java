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
     * @return
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
}
