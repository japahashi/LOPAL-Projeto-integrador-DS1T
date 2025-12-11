package br.com.estacionafacil.gui;

import br.com.estacionafacil.model.EntradaVeiculo;
import br.com.estacionafacil.model.EstacionaFacilApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.time.Duration;
import java.time.LocalDateTime;

public class TelaSaida extends Stage {

    public Scene telaSaida(Stage stage) {

        VBox root = new VBox();

        // Header da tela
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #FCA311;");
        header.setPrefHeight(85);
        Label labelTitulo = new Label("Controle de saída");
        labelTitulo.setStyle("-fx-text-fill: black;-fx-font-size: 25;");
        labelTitulo.setPadding(new Insets(25, 0, 0, 40));
        header.getChildren().add(labelTitulo);

        // Dropdown de veículos ativos
        ComboBox<EntradaVeiculo> carrosNoEstacionameto = new ComboBox<>();
        ObservableList<EntradaVeiculo> listaDeCarros = FXCollections.observableArrayList();
        carrosNoEstacionameto.setItems(listaDeCarros);
        carrosNoEstacionameto.setPromptText("Selecione um carro");

        // Configura o dropdown para mostrar apenas a placa
        carrosNoEstacionameto.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(EntradaVeiculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getPlaca());
            }
        });
        carrosNoEstacionameto.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(EntradaVeiculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getPlaca());
            }
        });
        // Preenche dropdown
        carrosNoEstacionameto.getItems().setAll(EstacionaFacilApp.getVeiculosAtivos());

        // TableView para mostrar tempo de permanência e valor a pagar
        TableView<EntradaVeiculo> carrosEstacionados = new TableView<>();
        carrosEstacionados.setPadding(new Insets(20, 300, 0, 300));

        // Coluna tempo de permanência
        TableColumn<EntradaVeiculo, String> tempoDePermanecia = new TableColumn<>("Tempo de permanência");
        tempoDePermanecia.setPrefWidth(200);
        tempoDePermanecia.setCellValueFactory(c -> {
            EntradaVeiculo v = c.getValue();
            return new SimpleStringProperty(formatTempo(v));
        });

        // Coluna valor a pagar
        TableColumn<EntradaVeiculo, String> valorAPagar = new TableColumn<>("Valor a pagar");
        valorAPagar.setPrefWidth(230);
        valorAPagar.setCellValueFactory(c -> {
            EntradaVeiculo v = c.getValue();
            if (v.getValorPagar() != null) {
                return new SimpleStringProperty(String.format("R$ %.2f", v.getValorPagar()));
            } else {
                return new SimpleStringProperty("");
            }
        });

        carrosEstacionados.getColumns().addAll(tempoDePermanecia, valorAPagar);

        // Container para botões
        VBox containerBotao = new VBox(10);

        // Botão voltar
        Button voltar = new Button("Voltar");
        voltar.setStyle("-fx-background-color: #14213D; -fx-text-fill: white;");
        voltar.setPrefHeight(40);
        voltar.setPrefWidth(150);
        voltar.setOnAction(e -> {
            TelaEstacionaFacil tela = new TelaEstacionaFacil();
            tela.start(stage); // Volta para o dashboard
        });

        // Botão confirmar saída
        Button botaoConfirmarSaida = new Button("Confirmar pagamento/sáida");
        botaoConfirmarSaida.setStyle("-fx-background-color: #FCA311; -fx-text-fill: black;");
        botaoConfirmarSaida.setPrefHeight(60);
        botaoConfirmarSaida.setPrefWidth(170);

        // Ação ao clicar em confirmar saída
        botaoConfirmarSaida.setOnAction(e -> {
            EntradaVeiculo selecionado = carrosNoEstacionameto.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                try {
                    // Registra saída e calcula valor
                    double valor = EstacionaFacilApp.registrarSaida(selecionado.getPlaca());

                    // Atualiza tabela com tempo e valor do veículo removido
                    carrosEstacionados.getItems().clear();
                    carrosEstacionados.getItems().add(selecionado);

                    // Atualiza dropdown
                    carrosNoEstacionameto.getItems().setAll(EstacionaFacilApp.getVeiculosAtivos());

                } catch (Exception exception) {
                    System.out.println(" Erro: " + exception.getMessage());
                }
            }
        });

        containerBotao.getChildren().addAll(botaoConfirmarSaida, voltar);
        containerBotao.setAlignment(Pos.CENTER);
        containerBotao.setPadding(new Insets(0, 0, 0, 0));

        // Adiciona tudo ao root
        root.getChildren().add(header);
        root.getChildren().add(carrosNoEstacionameto);
        root.getChildren().add(carrosEstacionados);
        root.getChildren().add(containerBotao);

        return new Scene(root, 1030, 600);
    }


    // Metodo para formatar em tempo real
    private String formatTempo(EntradaVeiculo v) {
        LocalDateTime inicio = v.getHoraEntrada();
        LocalDateTime fim = v.getHoraSaida() != null ? v.getHoraSaida() : LocalDateTime.now();
        long minutos = Duration.between(inicio, fim).toMinutes();
        long horas = minutos / 60;
        long mins = minutos % 60;
        return String.format("%dh %02dmin", horas, mins);
    }
}



