package tarefa.biblioteca_jogos.model;

public class Jogo {
    private String titulo;
    private String genero;
    private int anoLancamento;

    public Jogo(String titulo, String genero, int anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", titulo, genero, anoLancamento);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }
}
