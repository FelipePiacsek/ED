package arvore;

public class ValorPresenteException extends Exception {

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 1952814294372158741L;

    private String mensagem;

    public ValorPresenteException(Integer numero) {
        this.mensagem = "O valor " + numero + " j� est� presente na �rvore.";
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }

}
