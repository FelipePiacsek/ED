package arvore;

public class ArvoreNaoExistenteException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 9112772868761090866L;

    private String mensagem;

    public ArvoreNaoExistenteException(Integer numero) {
        this.mensagem = "N�o h� n� com valor " + numero + " presente na �rvore.";
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
