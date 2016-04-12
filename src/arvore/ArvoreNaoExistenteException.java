package arvore;

public class ArvoreNaoExistenteException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 9112772868761090866L;

    private String mensagem;

    public ArvoreNaoExistenteException(Integer numero) {
        this.mensagem = "Não há nó com valor " + numero + " presente na árvore.";
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
