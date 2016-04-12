package arvore;

import java.util.Collections;
import java.util.List;

public class Teste {

    public static void main(String[] args) {
        int[] mockContent = {50, 80, 90, 85, 75, 77, 78, 79, 60, 0, 12, 150, 125};
        ArvoreBinariaBusca arvore = testeInsercao(100, mockContent);
        final List<ArvoreBinariaBusca> all = arvore.todos();
        Collections.reverse(all);
        testeRemocao(arvore, all);

    }

    private static ArvoreBinariaBusca testeInsercao(int raiz, int[] subsequentes) {
        ArvoreBinariaBusca teste = new ArvoreBinariaBusca(raiz);
        for (int n : subsequentes) {
            teste.inserir(n);
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

    public static void testeRemocao(ArvoreBinariaBusca arvore, List<ArvoreBinariaBusca> valores) {
        System.out.println(arvore.eRd());
        for (ArvoreBinariaBusca a : valores) {
            arvore.remover(a);
            System.out.println(arvore.eRd());
        }
    }
}
