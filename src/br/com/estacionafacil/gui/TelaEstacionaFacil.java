package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EstacionaFacil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEstacionaFacil extends Application {


    public void start(Stage stage) throws Exception {

        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.setStyle("-fx-background-color: white;");

        stage.setWidth(1030);
        stage.setHeight(600);

        VBox header = new VBox();

        stage.setTitle("Estaciona Fácil");

        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);

        Label labelTitulo = new Label("Estaciona Facil");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;fx-font-weight: bold;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));

        Label labelSubtitulo = new Label("Gestão de estacionamentos");
        labelSubtitulo.setPadding(new Insets(0, 0, 0,40));

        TableView<EstacionaFacil> carrosEstacionados = new TableView<>();
        carrosEstacionados.setPadding(new Insets(60, 300, 0,300));

        Button entrada = new Button("Nova entrada");








        header.getChildren().addAll(labelTitulo, labelSubtitulo);
        root.getChildren().add(header);
        root.getChildren().add(carrosEstacionados);

        stage.setScene(scene);
        stage.show();





    }
}
