module tarefa.biblioteca_jogos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens tarefa.biblioteca_jogos to javafx.fxml;
    exports tarefa.biblioteca_jogos;
}