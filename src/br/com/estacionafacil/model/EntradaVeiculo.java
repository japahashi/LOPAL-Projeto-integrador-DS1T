package br.com.estacionafacil.model;

import java.time.LocalDateTime;

public class EntradaVeiculo {

    private String placa;
    private String modelo;
    private String proprietario;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;


    public EntradaVeiculo(String placa, String modelo, String proprietario) {

        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.horaEntrada = LocalDateTime.now();
        this.horaSaida = null;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getProprietario() {
        return proprietario;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }


    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String toString() {
        return placa + " - " + modelo;
    }

}


