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

    public Scene telaEntrada (Stage stage) {

        VBox root = new VBox();


        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);
        Label labelTitulo = new Label("Controle de entrada");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));
        header.getChildren().add(labelTitulo);

        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(20);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(120, 0, 10, 350));

        Label labelPlaca = new Label("Placa do veìculo");
        TextField textFieldPlaca = new TextField();
        Label labelModeloCarro = new Label("Modelo do veìculo");
        TextField textFieldModeloCarro = new TextField();
        Label labelNomeProprietario = new Label("Nome do proprietario");
        TextField textFieldNomeProprietario = new TextField();

        gridFormulario.add(labelPlaca, 0, 0);
        gridFormulario.add(textFieldPlaca, 1, 0);
        gridFormulario.add(labelModeloCarro, 0, 1);
        gridFormulario.add(textFieldModeloCarro, 1, 1);
        gridFormulario.add(labelNomeProprietario, 0, 2);
        gridFormulario.add(textFieldNomeProprietario, 1, 2);

        Button botaoConfirmarEntrada = new Button("Confirmar entrada");
        botaoConfirmarEntrada.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        botaoConfirmarEntrada.setPrefHeight(60);
        botaoConfirmarEntrada.setPrefWidth(170);

        botaoConfirmarEntrada.setOnAction( e ->{
            String placa = textFieldPlaca.getText();
            String modelo = textFieldModeloCarro.getText();
            String proprietario = textFieldNomeProprietario.getText();

            try{
                EntradaVeiculo veiculo = EstacionaFacilApp.registrarEntrada(placa,modelo, proprietario);

            }
        });


        VBox containerBotao = new VBox(botaoConfirmarEntrada);
        containerBotao.setAlignment(Pos.CENTER);
        containerBotao.setPadding(new Insets(80, 0, 0, 0));

        root.getChildren().add(header);
        root.getChildren().addAll(gridFormulario, containerBotao);

        return new Scene(root, 1030, 600);

    }
}

