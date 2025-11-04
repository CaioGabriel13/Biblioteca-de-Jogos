package tarefa.biblioteca_jogos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import tarefa.biblioteca_jogos.controller.BibliotecaController;
import tarefa.biblioteca_jogos.util.CriterioOrdenacao;

public class HelloController {

    @FXML
    private TextField tituloField;

    @FXML
    private TextField generoField;

    @FXML
    private TextField anoField;

    @FXML
    private ComboBox<String> algoritmoCombo;

    @FXML
    private ComboBox<String> criterioCombo;

    @FXML
    private TextArea resultadoArea;

    @FXML
    private Label statusLabel;

    // Backend controller
    private BibliotecaController bibliotecaController;

    @FXML
    public void initialize() {
        bibliotecaController = new BibliotecaController();

        // Inicializa os ComboBoxes
        if (algoritmoCombo != null) {
            algoritmoCombo.getItems().addAll("BUBBLE", "INSERTION", "QUICK");
            algoritmoCombo.setValue("QUICK");
        }

        if (criterioCombo != null) {
            criterioCombo.getItems().addAll("TITULO", "GENERO", "ANO_LANCAMENTO");
            criterioCombo.setValue("TITULO");
        }

        statusLabel.setText("Sistema pronto!");
    }

    @FXML
    protected void onAdicionarClick() {
        try {
            String titulo = tituloField.getText();
            String genero = generoField.getText();
            int ano = Integer.parseInt(anoField.getText());

            String resultado = bibliotecaController.adicionarJogo(titulo, genero, ano);
            statusLabel.setText(resultado);

            // Limpa os campos
            tituloField.clear();
            generoField.clear();
            anoField.clear();

        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Ano inválido!");
        }
    }

    @FXML
    protected void onBuscarClick() {
        String titulo = tituloField.getText();
        String resultado = bibliotecaController.buscarJogo(titulo);
        resultadoArea.setText(resultado);
        statusLabel.setText("Busca realizada");
    }

    @FXML
    protected void onRemoverClick() {
        String titulo = tituloField.getText();
        String resultado = bibliotecaController.removerJogo(titulo);
        statusLabel.setText(resultado);
        tituloField.clear();
    }

    @FXML
    protected void onListarClick() {
        String algoritmo = algoritmoCombo.getValue();
        String criterioStr = criterioCombo.getValue();

        CriterioOrdenacao criterio = CriterioOrdenacao.valueOf(criterioStr);
        String resultado = bibliotecaController.listarJogosOrdenados(criterio, algoritmo);

        resultadoArea.setText(resultado);
        statusLabel.setText("Lista gerada com " + algoritmo);
    }

    @FXML
    protected void onEstatisticasClick() {
        String estatisticas = bibliotecaController.obterEstatisticas();
        resultadoArea.setText(estatisticas);
        statusLabel.setText("Estatísticas exibidas");
    }

    @FXML
    protected void onLimparClick() {
        String resultado = bibliotecaController.limparBiblioteca();
        statusLabel.setText(resultado);
        resultadoArea.clear();
    }
}