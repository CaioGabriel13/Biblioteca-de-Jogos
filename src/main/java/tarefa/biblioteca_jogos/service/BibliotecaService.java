package tarefa.biblioteca_jogos.service;

import tarefa.biblioteca_jogos.model.Jogo;
import tarefa.biblioteca_jogos.util.CriterioOrdenacao;

public class BibliotecaService {
    private static TabelaHash tabelaHash;

    public BibliotecaService(int tamanhoTabela) {
        this.tabelaHash = new TabelaHash(tamanhoTabela);
    }

    public BibliotecaService() {
        this.tabelaHash = new TabelaHash();
    }

    public boolean adicionarJogo(Jogo jogo) {
        return tabelaHash.inserir(jogo);
    }

    public static Jogo buscarJogo(String titulo) {
        return tabelaHash.buscar(titulo);
    }

    public boolean removerJogo(String titulo) {
        return tabelaHash.remover(titulo);
    }

    public Jogo[] listarJogosOrdenadosBubbleSort(CriterioOrdenacao criterio) {
        Jogo[] jogos = tabelaHash.exportarParaVetor();
        AlgoritmosOrdenacao.bubbleSort(jogos, criterio);
        return jogos;
    }

    public Jogo[] listarJogosOrdenadosInsertionSort(CriterioOrdenacao criterio) {
        Jogo[] jogos = tabelaHash.exportarParaVetor();
        AlgoritmosOrdenacao.insertionSort(jogos, criterio);
        return jogos;
    }

    public Jogo[] listarJogosOrdenadosQuickSort(CriterioOrdenacao criterio) {
        Jogo[] jogos = tabelaHash.exportarParaVetor();
        AlgoritmosOrdenacao.quickSort(jogos, criterio);
        return jogos;
    }

    public int getQuantidadeJogos() {
        return tabelaHash.getQuantidadeElementos();
    }

    public boolean estaVazia() {
        return tabelaHash.estaVazia();
    }

    public void limparBiblioteca() {
        tabelaHash.limpar();
    }

    public String getEstatisticas() {
        return tabelaHash.getEstatisticas();
    }

    public Jogo[] exportarJogos() {
        return tabelaHash.exportarParaVetor();
    }
}