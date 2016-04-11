package arvore;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Representa uma �rvore bin�ria de busca. Cont�m opera��o de busca e inser��o.
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
     * M�todo que verifica se a �rvore cont�m um dado conte�do via busca eRd.
     * @param arvore
     * @return booleano que informa se dado inteiro est� ou n�o presente na �rvore.
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
     * Insere um valor na �rvore. N�o � permitida a inser��o de valores j� presentes na �rvore.
     * @param valor a ser inserido.
     * @return raiz da �rvore.
     * @throws ValorPresenteException exce��o lan�ada quando o valor a ser inserido j� est� na �rvore.
     */
    public ArvoreBinariaBusca inserir(Integer valor) throws ValorPresenteException {
        if (this.valor == null) {
            this.valor = valor;
            return this;
        }
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
     * Remove um n� da �rvore, mantendo as propriedades de uma �rvore Bin�ria de Busca.
     * @param no n� a ser removido da �rvore.
     * @return
     * @throws ArvoreNaoExistenteException Se o n� da �rvore n�o estiver presente, lan�a exception.
     */
    public ArvoreBinariaBusca remover(ArvoreBinariaBusca no) throws ArvoreNaoExistenteException {

        boolean existe = this.equals(no);
        if (existe) {
            if (direita != null) {
                this.valor = direita.valor;
                this.esquerda = direita.esquerda;
                this.direita = direita.direita;
                return this;
            } else if (esquerda == null) {
                this.valor = null;
                this.esquerda = null;
                this.direita = null;
                return this;
            }
        }
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
        ArvoreBinariaBusca substituto = cursor.direita;
        ArvoreBinariaBusca paiMaiorDentreMenores = obterPaiMaiorDentreMenores(cursor);
        if (paiMaiorDentreMenores != null) {
            if (paiMaiorDentreMenores.direita != null) {
                substituto = paiMaiorDentreMenores.direita;
                paiMaiorDentreMenores.direita = substituto.esquerda;
                substituto.direita = cursor.direita;
                substituto.esquerda = cursor.esquerda;
            } else {
                substituto = paiMaiorDentreMenores;
            }
        }

        if (substituto != null) {
            if (anteriorCursor.valor.equals(no.valor)) {
                this.valor = substituto.valor;
                this.esquerda = substituto.esquerda;
                this.direita = substituto.direita;
            } else if (anteriorCursor.valor > substituto.valor) {
                anteriorCursor.esquerda = substituto;
            } else {
                anteriorCursor.direita = substituto;
            }
        } else {
            if (anteriorCursor.valor > no.valor) {
                anteriorCursor.esquerda = null;
            } else {
                anteriorCursor.direita = null;
            }
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
        if (this.valor == null) {
            return "";
        }
        ArvoreBinariaBusca cursor = this;
        StringBuilder erd = new StringBuilder(cursor.valor);
        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!pilha.isEmpty()) {
                cursor = pilha.pop();
                erd.append(cursor.valor);
                erd.append("  ");
                cursor = cursor.getDireita();
            }
            while (cursor != null) {
                pilha.push(cursor);
                cursor = cursor.getEsquerda();
            }
        } while (!pilha.isEmpty());
        return erd.toString();
    }

    public List<ArvoreBinariaBusca> todos() {
        ArvoreBinariaBusca cursor = this;
        List<ArvoreBinariaBusca> erdList = new ArrayList<ArvoreBinariaBusca>();
        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!pilha.isEmpty()) {
                cursor = pilha.pop();
                erdList.add(cursor);
                cursor = cursor.getDireita();
            }
            while (cursor != null) {
                pilha.push(cursor);
                cursor = cursor.getEsquerda();
            }
        } while (!pilha.isEmpty());
        return erdList;
    }

    public Integer quantidadeNos() {
        Integer quantidade = 0;
        ArvoreBinariaBusca cursor = this;
        Stack<ArvoreBinariaBusca> pilha = new Stack<ArvoreBinariaBusca>();
        do {
            if (!pilha.isEmpty()) {
                cursor = pilha.pop();
                quantidade++;
                cursor = cursor.getDireita();
            }
            while (cursor != null) {
                if (cursor.valor != null) {
                    pilha.push(cursor);
                }
                cursor = cursor.getEsquerda();
            }
        } while (!pilha.isEmpty());
        return quantidade;
    }

    public boolean vazia() {
        return this.valor == null;
    }

    public boolean folha() {
        return this.esquerda == null && this.direita == null;
    }

    public void esvaziar() {
        this.valor = null;
        this.esquerda = null;
        this.direita = null;
    }

    @Override
    public boolean equals(Object obj) {
        final Integer v = ((ArvoreBinariaBusca) obj).valor;
        return v != null && this.valor != null && v == this.valor;
    }
}
