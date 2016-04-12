package arvore;

public abstract class ArvoreUtils {

    public static ArvoreBinariaBusca mock() {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca(100);
        int[] mockContent = {50, 80, 75, 60, 0, 12, 150, 125};
        for (int n : mockContent) {
            arvore.inserir(n);
        }
        return arvore;
    }
}
