module tarefa.biblioteca_jogos {
    requires javafx.controls;
    requires javafx.fxml;

    // Exporta os pacotes para que possam ser acessados por outros módulos
    exports tarefa.biblioteca_jogos;
    exports tarefa.biblioteca_jogos.controller;
    exports tarefa.biblioteca_jogos.model;
    exports tarefa.biblioteca_jogos.service;
    exports tarefa.biblioteca_jogos.util;

    // Abre os pacotes para JavaFX FXML (necessário para reflection)
    opens tarefa.biblioteca_jogos to javafx.fxml;
    opens tarefa.biblioteca_jogos.controller to javafx.fxml;
}