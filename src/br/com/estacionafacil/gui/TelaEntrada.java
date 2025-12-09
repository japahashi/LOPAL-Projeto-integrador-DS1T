package br.com.estacionafacil.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEntrada extends Stage {

    public Scene telaEntrada (Stage stage) {

        VBox root = new VBox();


        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);
        Label labelTitulo = new Label("Controle de saída");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));
        header.getChildren().add(labelTitulo);

        


        root.getChildren().add(header);


        return new Scene(root, 1030, 600);

    }
}

