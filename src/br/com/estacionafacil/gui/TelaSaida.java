package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EstacionaFacilApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaSaida extends Stage {

    public Scene telaSaida (Stage stage) {

        VBox root = new VBox();

        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);
        Label labelTitulo = new Label("Controle de saída");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));
        header.getChildren().add(labelTitulo);

        ComboBox<EstacionaFacilApp> carrosNoEstacionameto = new ComboBox<>();
        ObservableList<EstacionaFacilApp> listaDeCarros = FXCollections.observableArrayList();
        carrosNoEstacionameto.setItems(listaDeCarros);
        carrosNoEstacionameto.setPromptText("Selecione um carro");



        TableView<EstacionaFacilApp> carrosEstacionados = new TableView<>();
        carrosEstacionados.setPadding(new Insets(20, 300, 0,300));

        TableColumn<EstacionaFacilApp, String> tempoDePermanecia = new TableColumn<>("Tempo de permanecia");
        TableColumn<EstacionaFacilApp, String> valorAPagar = new TableColumn<>("Valor a pagar");
        tempoDePermanecia.setPrefWidth(200);
        valorAPagar.setPrefWidth(230);

        carrosEstacionados.getColumns().addAll(tempoDePermanecia, valorAPagar);

        Button botaoConfirmarSaida = new Button("Confirmar pagamento/sáida");
        botaoConfirmarSaida.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        botaoConfirmarSaida.setPrefHeight(60);
        botaoConfirmarSaida.setPrefWidth(170);

        VBox containerBotao = new VBox(botaoConfirmarSaida);
        containerBotao.setAlignment(Pos.CENTER);
        containerBotao.setPadding(new Insets(0, 0, 0, 0));

        root.getChildren().add(header);
        root.getChildren().add(carrosNoEstacionameto);
        root.getChildren().add(carrosEstacionados);
        root.getChildren().add(containerBotao);


        return new Scene(root, 1030, 600);

    }
}

