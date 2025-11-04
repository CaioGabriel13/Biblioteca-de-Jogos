package tarefa.biblioteca_jogos.service;

import tarefa.biblioteca_jogos.model.Jogo;
import tarefa.biblioteca_jogos.util.CriterioOrdenacao;

public class AlgoritimosOrdenacao {
    public static void bubbleSort(Jogo[] jogos, CriterioOrdenacao criterio) {
        if (jogos == null || jogos.length <= 1) {
            return;
        }

        int n = jogos.length;
        boolean houveTroca;

        for (int i = 0; i < n - 1; i++) {
            houveTroca = false;

            for (int j = i + 1; j < n; j++) {
                if (comparar(jogos[j], jogos[j + 1], criterio) > 0){
                    Jogo temp = jogos[j];
                    jogos[j] = jogos[j + 1];
                    jogos[j + 1] = temp;
                    houveTroca = true;
                }
            }
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
