package br.com.estacionafacil.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEstacionaFacil extends Application {


    public void start(Stage stage) throws Exception {

        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.setStyle("-fx-background-color: white;");

        stage.setWidth(1030);
        stage.setHeight(600);

        stage.setTitle("Estaciona Facil");

        VBox header = new VBox();

        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);
        Label labelTitulo = new Label("Estaciona Facil");
        labelTitulo.setStyle("-fx-text-fill: black;");
        labelTitulo.setPadding(new Insets(35, 0, 15,10));
        header.getChildren().addAll(labelTitulo);


        root.getChildren().add(header);

        stage.setScene(scene);
        stage.show();





    }
}
