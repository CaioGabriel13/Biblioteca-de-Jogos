package tarefa.biblioteca_jogos.model;

public class Node {
    private Jogo jogo;
    private Node proximo;

    public Node(Jogo jogo) {
        this.jogo = jogo;
        this.proximo = null;
    }

    public Node(Jogo jogo, Node proximo) {
        this.jogo = jogo;
        this.proximo = proximo;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
