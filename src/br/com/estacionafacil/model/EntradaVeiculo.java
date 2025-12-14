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

    // Formato padrão de data e hora
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Construtor chamado no momento da entrada
    public EntradaVeiculo(String placa, String modelo, String proprietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.horaEntrada = LocalDateTime.now();
        this.valorPagar = null;
    }

    // Getters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getProprietario() { return proprietario; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSaida() { return horaSaida; }

    // Setter da hora de saída
    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    // Setter e getter do valor
    public void setValorPagar(double valor) {
        this.valorPagar = valor;
    }

    public Double getValorPagar() {
        return valorPagar;
    }

    // Formata data/hora
    public String getHoraEntradaFormatada() {
        return horaEntrada.format(FORMATTER);
    }

    public String getHoraSaidaFormatada() {
        if (horaSaida == null) return "";
        return horaSaida.format(FORMATTER);
    }
}





