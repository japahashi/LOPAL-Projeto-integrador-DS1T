package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EntradaVeiculo;
import br.com.estacionafacil.model.EstacionaFacilApp;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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

    @Override
    public void start(Stage stage) {

        // Layout principal vertical
        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: white;"); // Fundo branco

        // Configurações da janela
        stage.setTitle("Estaciona Fácil");
        stage.setWidth(1030);
        stage.setHeight(600);

        // Header da tela
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);

        Label labelTitulo = new Label("Estaciona Facil");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0,40));

        Label labelSubtitulo = new Label("Gestão de estacionamentos");
        labelSubtitulo.setPadding(new Insets(-5, 0, 20,40));
        header.getChildren().addAll(labelTitulo, labelSubtitulo);

        // Tabela que mostra os veículos ativos
        TableView<EntradaVeiculo> tabela = new TableView<>();
        tabela.setPadding(new Insets(60, 300, 0,300));

        // Colunas da tabela
        TableColumn<EntradaVeiculo, String> placa = new TableColumn<>("Placa");
        placa.setPrefWidth(80);
        placa.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlaca()));

        TableColumn<EntradaVeiculo, String> horaEntrada = new TableColumn<>("Hora de entrada");
        horaEntrada.setPrefWidth(115);
        horaEntrada.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getHoraEntrada().toLocalTime().toString())
        );

        TableColumn<EntradaVeiculo, String> dataEntrada = new TableColumn<>("Data de entrada");
        dataEntrada.setPrefWidth(115);
        dataEntrada.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getHoraEntrada().toLocalDate().toString())
        );

        TableColumn<EntradaVeiculo, String> modelo = new TableColumn<>("Modelo do carro");
        modelo.setPrefWidth(105);
        modelo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getModelo()));

        tabela.getColumns().addAll(placa, horaEntrada, dataEntrada, modelo);
        tabela.getItems().setAll(EstacionaFacilApp.getVeiculosAtivos());

        // Botão para abrir tela de entrada
        Button entrada = new Button("Nova entrada");
        entrada.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        entrada.setPrefHeight(70);
        entrada.setPrefWidth(170);

        // Botão para abrir tela de saída
        Button saida = new Button("Registrar saida");
        saida.setStyle("-fx-background-color: #14213D; -fx-text-fill: white;");
        saida.setPrefHeight(70);
        saida.setPrefWidth(170);

        // Ação ao clicar no botão Nova entrada
        entrada.setOnAction(e -> {
            TelaEntrada tela = new TelaEntrada();
            stage.setScene(tela.telaEntrada(stage)); // Substitui a cena atual
        });

        // Ação ao clicar no botão Registrar saída
        saida.setOnAction(e -> {
            TelaSaida tela = new TelaSaida();

            // Substitui a cena atual com tela de saída
            stage.setScene(tela.telaSaida(stage));
        });

        // Layout horizontal para os botões
        HBox boxButtons = new HBox(50, entrada, saida);
        boxButtons.setPadding(new Insets(20,0,20,0));
        boxButtons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(header, tabela, boxButtons);

        stage.setScene(scene);
        stage.show();
    }
}



