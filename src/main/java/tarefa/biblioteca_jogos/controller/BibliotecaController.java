package tarefa.biblioteca_jogos.controller;

import tarefa.biblioteca_jogos.model.Jogo;
import tarefa.biblioteca_jogos.service.BibliotecaService;
import tarefa.biblioteca_jogos.util.CriterioOrdenacao;

public class BibliotecaController {

    private BibliotecaService bibliotecaService;

    public BibliotecaController() {
        this.bibliotecaService = new BibliotecaService();
    }

    public BibliotecaController(int tamanhoTabela) {
        this.bibliotecaService = new BibliotecaService(tamanhoTabela);
    }

    public String adicionarJogo(String titulo, String genero, int anoLancamento) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return "Erro: titulo não pode ser vazio";
        }
        if (genero == null || genero.trim().isEmpty()) {
            return "Erro: Gênero não pode ser vazio";
        }
        if (anoLancamento < 1958 || anoLancamento > 2030) {
            return  "Erro: Ano de Lançamento inválido";
        }

        Jogo jogo = new Jogo(titulo.trim(), genero.trim(), anoLancamento);
        boolean sucesso = bibliotecaService.adicionarJogo(jogo);

                if (sucesso) {
                    return "Jogo " + titulo + " adicionado com sucesso!";
                } else {
                    return "Erro: Jogo" + titulo + "já existe na biblioteca!";
                }

    }
    public String buscarJogo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return "Erro: Título não pode ser vazio.";
        }

        Jogo jogo = BibliotecaService.buscarJogo(titulo.trim());

        if (jogo != null) {
            return formatarJogo(jogo);
        } else {
            return "Jogo" + titulo + "não encontrado!";
        }
    }
    public String removerJogo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return "Erro: Título não pode ser vazio.";
        }

        boolean sucesso = bibliotecaService.removerJogo(titulo.trim());

        if (sucesso) {
            return "Jogo " + titulo + " removido com sucesso!";
        } else {
            return "Erro: Jogo " + titulo + " não encontrado.";
        }
    }
    public String listarJogosOrdenados(CriterioOrdenacao criterio, String algoritmo) {
        if (bibliotecaService.estaVazia()) {
            return "A biblioteca está vazia!";
        }

        Jogo[] jogos;
        String nomeAlgoritimo;

        switch (algoritmo.toUpperCase()){
            case "BUBBLE":
                jogos = bibliotecaService.listarJogosOrdenadosBubbleSort(criterio);
                nomeAlgoritimo = "BubbleSort";
                break;
            case "INSERTION":
                jogos = bibliotecaService.listarJogosOrdenadosInsertionSort(criterio);
                nomeAlgoritimo = "InsertionSort";
                break;
            case "QUICK":
                jogos = bibliotecaService.listarJogosOrdenadosQuickSort(criterio);
                nomeAlgoritimo = "QuickSort";
                break;
            default:
                return "Erro: Algoritmo inválido. Use BUBBLE, INSERTION ou QUICK.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Lista de Jogos (").append(nomeAlgoritimo).append(") ===\n");
        sb.append("Critério: ").append(criterio).append("\n");
        sb.append("Total: ").append(jogos.length).append(" jogos\n\n");

        for (int i = 0; i < jogos.length; i++) {
            sb.append(String.format("%3d. ", i + 1));
            sb.append(formatarJogo(jogos[i]));
            sb.append("\n");
        }

        return sb.toString();
    }
    public String obterEstatisticas() {
        return bibliotecaService.getEstatisticas();
    }

    public int getQuantidadeJogos() {
        return bibliotecaService.getQuantidadeJogos();
    }

    public String limparBiblioteca() {
        bibliotecaService.limparBiblioteca();
        return "Biblioteca limpa com sucesso!";
    }

    private String formatarJogo(Jogo jogo) {
        return String.format("%-40s | %-15s | %d",
                jogo.getTitulo(),
                jogo.getGenero(),
                jogo.getAnoLancamento());
    }

    public BibliotecaService getService() {
        return bibliotecaService;
    }
}
