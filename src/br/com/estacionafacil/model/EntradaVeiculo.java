package br.com.estacionafacil.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaVeiculo {

    private String placa;
    private String modelo;
    private String proprietario;

    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Double valorPagar;

    // Formato de data/hora para exibição
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Construtor
    public EntradaVeiculo(String placa, String modelo, String proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
        // Hora de entrada = momento atual
        this.horaEntrada = LocalDateTime.now();
        // Inicialmente nulo
        this.valorPagar = null;
    }

    // Getters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getProprietario() { return proprietario; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSaida() { return horaSaida; }

    // Setter da hora de saída
    public void setHoraSaida(LocalDateTime horaSaida) { this.horaSaida = horaSaida; }

    // Formata hora de entrada
    public String getHoraEntradaFormatada() { return horaEntrada.format(FORMATTER); }

    // Formata hora de saída
    public String getHoraSaidaFormatada() {
        if (horaSaida == null) return "";
        return horaSaida.format(FORMATTER);
    }

    // Setter e getter do valor a pagar
    public void setValorPagar(double valor) { this.valorPagar = valor; }
    public Double getValorPagar() { return valorPagar; }

    // Formata valor para exibição
    public String getValorFormatado() {
        if (valorPagar == null) return "";
        return "R$ " + String.format("%.2f", valorPagar);
    }
}




