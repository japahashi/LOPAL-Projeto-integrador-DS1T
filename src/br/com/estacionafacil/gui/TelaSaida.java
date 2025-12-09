package br.com.estacionafacil.gui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaSaida extends Stage {

    public Scene telaSaida (Stage stage) {

        VBox root = new VBox();

        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);


        root.getChildren().add(header);


        return new Scene(root, 1030, 600);

    }
}

