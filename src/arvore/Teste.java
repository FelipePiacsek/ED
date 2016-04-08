package arvore;

public class Teste {

    public static void main(String[] args) {
        int[] mockContent = {50, 80, 90, 85, 75, 77, 78, 79, 60, 0, 12, 150, 125};
        ArvoreBinariaBusca arvore = testeInsercao(100, mockContent);
        ArvoreBinariaBusca no = arvore.getEsquerda().getDireita();
        try {
            arvore.remover(no);
        } catch (ArvoreNaoExistenteException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(arvore.contem(80));

    }

    private static ArvoreBinariaBusca testeInsercao(int raiz, int[] subsequentes) {
        ArvoreBinariaBusca teste = new ArvoreBinariaBusca(raiz);
        for (int n : subsequentes) {
            try {
                teste.inserir(n);
            } catch (ValorPresenteException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return teste;
    }

    public static boolean testeContem(ArvoreBinariaBusca arvore, int[] valores) {
        boolean containsAll = true;
        for (int n : valores) {
            containsAll = containsAll && arvore.contem(n);
        }
        return containsAll;

    }
}
