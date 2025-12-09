package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EstacionaFacil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaEstacionaFacil extends Application {


    public void start(Stage stage) throws Exception {

        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: white;");

        stage.setTitle("Estaciona Fácil");
        stage.setWidth(1030);
        stage.setHeight(600);

        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);

        Label labelTitulo = new Label("Estaciona Facil");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));

        Label labelSubtitulo = new Label("Gestão de estacionamentos");
        labelSubtitulo.setPadding(new Insets(-5, 0, 20,40));
        header.getChildren().addAll(labelTitulo, labelSubtitulo);

        TableView<EstacionaFacil> carrosEstacionados = new TableView<>();
        carrosEstacionados.setPadding(new Insets(60, 300, 0,300));

        TableColumn<EstacionaFacil, String> placaDoCarro = new TableColumn<>("Placa");
        TableColumn<EstacionaFacil, String> horarioDeEntrada = new TableColumn<>("Hora de entrada");
        TableColumn<EstacionaFacil, String> dataDeEntrada = new TableColumn<>("Data de entrada");
        TableColumn<EstacionaFacil, String> modeloDoCarro = new TableColumn<>("Modelo do carro");
        placaDoCarro.setPrefWidth(80);
        horarioDeEntrada.setPrefWidth(115);
        dataDeEntrada.setPrefWidth(115);
        modeloDoCarro.setPrefWidth(105);
        carrosEstacionados.getColumns().addAll(placaDoCarro, horarioDeEntrada, dataDeEntrada, modeloDoCarro);

        Button entrada = new Button("Nova entrada");
        entrada.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        entrada.setPrefHeight(70);
        entrada.setPrefWidth(170);

        Button saida = new Button("Registrar saida");
        saida.setStyle("-fx-background-color: #14213D; -fx-text-fill: white;");
        saida.setPrefHeight(70);
        saida.setPrefWidth(170);

        entrada.setOnAction(e -> {
            TelaEntrada tela = new TelaEntrada();
            stage.setScene(tela.telaEntrada(stage));
        });

        saida.setOnAction(e -> {
            TelaSaida tela = new TelaSaida();
            stage.setScene(tela.telaSaida(stage));
        });

        HBox boxButtons = new HBox();
        boxButtons.setSpacing(50);
        boxButtons.setPadding(new Insets(20, 0, 20, 0));
        boxButtons.setStyle("-fx-fill-color: #FCA311;");
        boxButtons.setAlignment(Pos.CENTER);
        boxButtons.getChildren().addAll(entrada,saida);










        root.getChildren().add(header);
        root.getChildren().add(carrosEstacionados);
        root.getChildren().add(boxButtons);


        stage.setScene(scene);
        stage.show();





    }
}
