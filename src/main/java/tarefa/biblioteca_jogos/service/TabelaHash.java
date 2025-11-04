package tarefa.biblioteca_jogos.service;

import tarefa.biblioteca_jogos.model.Jogo;
import tarefa.biblioteca_jogos.model.Node;

public class TabelaHash {
    private Node[] tabela;
    private int tamanho;
    private int quantidadeElementos;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
        this.quantidadeElementos = 0;
    }

    public TabelaHash() {
        this(101);
    }

    private int funcaoHash(Jogo jogo) {
        int hash = Math.abs(jogo.hashCode());
        return hash % tamanho;
    }

    public boolean inserir(Jogo jogo) {
        if (jogo == null) {
            return false;
        }

        int indice = funcaoHash(jogo);
        Node atual = tabela[indice];

        // Verifica se o jogo já existe na lista
        while (atual != null) {
            if (atual.getJogo().equals(jogo)) {
                return false; // Jogo já existe
            }
            atual = atual.getProximo();
        }

        // Insere no início da lista (mais eficiente)
        Node novoNode = new Node(jogo, tabela[indice]);
        tabela[indice] = novoNode;
        quantidadeElementos++;
        return true;
    }

    public Jogo buscar(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return null;
        }

        // Cria um jogo temporário para calcular o hash
        Jogo jogoTemp = new Jogo(titulo, "", 0);
        int indice = funcaoHash(jogoTemp);
        Node atual = tabela[indice];

        // Percorre a lista encadeada
        while (atual != null) {
            if (atual.getJogo().getTitulo().equalsIgnoreCase(titulo)) {
                return atual.getJogo();
            }
            atual = atual.getProximo();
        }

        return null; // Não encontrado
    }

    public boolean remover(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return false;
        }

        // Cria um jogo temporário para calcular o hash
        Jogo jogoTemp = new Jogo(titulo, "", 0);
        int indice = funcaoHash(jogoTemp);
        Node atual = tabela[indice];
        Node anterior = null;

        // Percorre a lista encadeada
        while (atual != null) {
            if (atual.getJogo().getTitulo().equalsIgnoreCase(titulo)) {
                // Remove o nó
                if (anterior == null) {
                    // Remover o primeiro nó da lista
                    tabela[indice] = atual.getProximo();
                } else {
                    // Remover nó do meio ou fim
                    anterior.setProximo(atual.getProximo());
                }
                quantidadeElementos--;
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        return false; // Não encontrado
    }

    public Jogo[] exportarParaVetor() {
        Jogo[] jogos = new Jogo[quantidadeElementos];
        int posicao = 0;

        // Percorre toda a tabela
        for (int i = 0; i < tamanho; i++) {
            Node atual = tabela[i];
            // Percorre a lista encadeada em cada posição
            while (atual != null) {
                jogos[posicao++] = atual.getJogo();
                atual = atual.getProximo();
            }
        }

        return jogos;
    }

    public int getQuantidadeElementos() {
        return quantidadeElementos;
    }

    public boolean estaVazia() {
        return quantidadeElementos == 0;
    }

    public void limpar() {
        tabela = new Node[tamanho];
        quantidadeElementos = 0;
    }

    public String getEstatisticas() {
        int posicoesCheias = 0;
        int maiorLista = 0;

        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                posicoesCheias++;
                int tamanhoLista = 0;
                Node atual = tabela[i];
                while (atual != null) {
                    tamanhoLista++;
                    atual = atual.getProximo();
                }
                if (tamanhoLista > maiorLista) {
                    maiorLista = tamanhoLista;
                }
            }
        }

        double fatorCarga = (double) quantidadeElementos / tamanho;

        return String.format(
                "Estatísticas da Tabela Hash:\n" +
                        "- Tamanho da tabela: %d\n" +
                        "- Elementos armazenados: %d\n" +
                        "- Posições ocupadas: %d\n" +
                        "- Fator de carga: %.2f\n" +
                        "- Maior lista encadeada: %d",
                tamanho, quantidadeElementos, posicoesCheias, fatorCarga, maiorLista
        );
    }
}