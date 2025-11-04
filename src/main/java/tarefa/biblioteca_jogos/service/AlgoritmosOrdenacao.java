package tarefa.biblioteca_jogos.service;

import tarefa.biblioteca_jogos.model.Jogo;
import tarefa.biblioteca_jogos.util.CriterioOrdenacao;

public class AlgoritmosOrdenacao {

    public static void bubbleSort(Jogo[] jogos, CriterioOrdenacao criterio) {
        if (jogos == null || jogos.length <= 1) {
            return;
        }

        int n = jogos.length;
        boolean houveTroca;

        for (int i = 0; i < n - 1; i++) {
            houveTroca = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (comparar(jogos[j], jogos[j + 1], criterio) > 0) {
                    // Troca os elementos
                    Jogo temp = jogos[j];
                    jogos[j] = jogos[j + 1];
                    jogos[j + 1] = temp;
                    houveTroca = true;
                }
            }

            // Se não houve troca, o vetor já está ordenado
            if (!houveTroca) {
                break;
            }
        }
    }

    public static void insertionSort(Jogo[] jogos, CriterioOrdenacao criterio) {
        if (jogos == null || jogos.length <= 1) {
            return;
        }

        int n = jogos.length;

        for (int i = 1; i < n; i++) {
            Jogo chave = jogos[i];
            int j = i - 1;

            // Move os elementos maiores que a chave uma posição à frente
            while (j >= 0 && comparar(jogos[j], chave, criterio) > 0) {
                jogos[j + 1] = jogos[j];
                j--;
            }

            jogos[j + 1] = chave;
        }
    }

    public static void quickSort(Jogo[] jogos, CriterioOrdenacao criterio) {
        if (jogos == null || jogos.length <= 1) {
            return;
        }
        quickSortRecursivo(jogos, 0, jogos.length - 1, criterio);
    }

    private static void quickSortRecursivo(Jogo[] jogos, int inicio, int fim, CriterioOrdenacao criterio) {
        if (inicio < fim) {
            // Particiona o vetor e obtém o índice do pivô
            int indicePivo = particionar(jogos, inicio, fim, criterio);

            // Ordena recursivamente as duas metades
            quickSortRecursivo(jogos, inicio, indicePivo - 1, criterio);
            quickSortRecursivo(jogos, indicePivo + 1, fim, criterio);
        }
    }

    private static int particionar(Jogo[] jogos, int inicio, int fim, CriterioOrdenacao criterio) {
        Jogo pivo = jogos[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (comparar(jogos[j], pivo, criterio) <= 0) {
                i++;
                // Troca jogos[i] com jogos[j]
                Jogo temp = jogos[i];
                jogos[i] = jogos[j];
                jogos[j] = temp;
            }
        }

        // Coloca o pivô na posição correta
        Jogo temp = jogos[i + 1];
        jogos[i + 1] = jogos[fim];
        jogos[fim] = temp;

        return i + 1;
    }

    private static int comparar(Jogo jogo1, Jogo jogo2, CriterioOrdenacao criterio) {
        switch (criterio) {
            case TITULO:
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());

            case GENERO:
                // Compara primeiro por gênero, depois por título (desempate)
                int comparacaoGenero = jogo1.getGenero().compareToIgnoreCase(jogo2.getGenero());
                if (comparacaoGenero != 0) {
                    return comparacaoGenero;
                }
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());

            case ANO_LANCAMENTO:
                // Compara primeiro por ano, depois por título (desempate)
                int comparacaoAno = Integer.compare(jogo1.getAnoLancamento(), jogo2.getAnoLancamento());
                if (comparacaoAno != 0) {
                    return comparacaoAno;
                }
                return jogo1.getTitulo().compareToIgnoreCase(jogo2.getTitulo());

            default:
                return 0;
        }
    }

    public static Jogo[] copiarVetor(Jogo[] jogos) {
        if (jogos == null) {
            return null;
        }

        Jogo[] copia = new Jogo[jogos.length];
        System.arraycopy(jogos, 0, copia, 0, jogos.length);
        return copia;
    }
}