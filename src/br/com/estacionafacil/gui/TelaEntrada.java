package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EntradaVeiculo;
import br.com.estacionafacil.model.EstacionaFacilApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEntrada extends Stage {

    // Metodo que retorna a Scene da tela de entrada
    public Scene telaEntrada(Stage stage) {

        VBox root = new VBox(); // Layout vertical principal

        // Header da tela
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);

        Label labelTitulo = new Label("Controle de entrada");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));
        header.getChildren().add(labelTitulo);

        // Grid para o formulário de cadastro
        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(20);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(120, 0, 10, 350));

        // Labels e campos de entrada
        Label labelPlaca = new Label("Placa do veìculo");
        TextField textFieldPlaca = new TextField();

        Label labelModeloCarro = new Label("Modelo do veìculo");
        TextField textFieldModeloCarro = new TextField();

        Label labelNomeProprietario = new Label("Nome do proprietario");
        TextField textFieldNomeProprietario = new TextField();

        // Adiciona campos ao grid
        gridFormulario.add(labelPlaca, 0, 0);
        gridFormulario.add(textFieldPlaca, 1, 0);
        gridFormulario.add(labelModeloCarro, 0, 1);
        gridFormulario.add(textFieldModeloCarro, 1, 1);
        gridFormulario.add(labelNomeProprietario, 0, 2);
        gridFormulario.add(textFieldNomeProprietario, 1, 2);

        // Botão para confirmar entrada
        Button botaoConfirmarEntrada = new Button("Confirmar entrada");
        botaoConfirmarEntrada.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        botaoConfirmarEntrada.setPrefHeight(60);
        botaoConfirmarEntrada.setPrefWidth(170);

        // Ação ao clicar no botão
        botaoConfirmarEntrada.setOnAction(e -> {
            String placa = textFieldPlaca.getText();
            String modelo = textFieldModeloCarro.getText();
            String proprietario = textFieldNomeProprietario.getText();

            try {
                // Registra o veículo no sistema
                EntradaVeiculo veiculo = EstacionaFacilApp.registrarEntrada(placa, modelo, proprietario);
                System.out.println("Veículo cadastrado: " + veiculo.getPlaca());

                // Volta para a tela principal
                TelaEstacionaFacil tela = new TelaEstacionaFacil();
                tela.start(stage);

            } catch (Exception exception) {
                System.out.println("Erro: " + exception.getMessage());
            }
        });

        // Botão voltar
        Button voltar = new Button("Voltar");
        voltar.setStyle("-fx-background-color: #14213D; -fx-text-fill: white;");
        voltar.setPrefHeight(40);
        voltar.setPrefWidth(150);

        // Ação para voltar à tela principal
        voltar.setOnAction(e -> {
            TelaEstacionaFacil tela = new TelaEstacionaFacil();
            try {
                tela.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Container vertical para os botões
        VBox containerBotao = new VBox(20, botaoConfirmarEntrada, voltar);
        containerBotao.setAlignment(Pos.CENTER);
        containerBotao.setPadding(new Insets(80, 0, 0, 0));

        // Adiciona componentes ao root
        root.getChildren().add(header);
        root.getChildren().addAll(gridFormulario, containerBotao);
        // Retorna a Scene completa
        return new Scene(root, 1030, 600);
    }
}



