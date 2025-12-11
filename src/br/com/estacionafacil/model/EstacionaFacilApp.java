package br.com.estacionafacil.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionaFacilApp {

    // Lista de veículos atualmente no estacionamento
    private static List<EntradaVeiculo> veiculosAtivos = new ArrayList<>();

    // Metodo para registrar entrada de veículo
    public static EntradaVeiculo registrarEntrada(String placa, String modelo, String proprietario) throws Exception {
        // Verifica se o veículo já está na lista
        for (EntradaVeiculo veiculo : veiculosAtivos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                throw new Exception("Veículo já está no estacionamento!");
            }
        }

        EntradaVeiculo veiculo = new EntradaVeiculo(placa, modelo, proprietario);
        veiculosAtivos.add(veiculo); // Adiciona à lista
        return veiculo;
    }

    // Metodo para registrar saída do veículo
    public static double registrarSaida(String placa) throws Exception {
        EntradaVeiculo veiculoEncontrado = null;

        // Procura veículo na lista
        for (EntradaVeiculo veiculo : veiculosAtivos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoEncontrado = veiculo;
                break;
            }
        }

        if (veiculoEncontrado == null) {
            throw new Exception("Veículo não encontrado!");
        }

        LocalDateTime saida = LocalDateTime.now();
        // Marca hora de saída
        veiculoEncontrado.setHoraSaida(saida);

        // Calcula tempo total em minutos
        long minutos = Duration.between(veiculoEncontrado.getHoraEntrada(), saida).toMinutes();
        long horasPagas = 1;

        if (minutos > 60) {
            long extra = minutos - 60;
            horasPagas += extra / 60;

            if (extra % 60 >= 5) { // Arredonda se exceder 5 minutos
                horasPagas++;
            }
        }

        double valor = 10 + (horasPagas - 1) * 5; // Valor total

        veiculoEncontrado.setValorPagar(valor); // Salva valor no objeto

        veiculosAtivos.remove(veiculoEncontrado); // Remove veículo da lista

        return valor;
    }

    // Retorna lista de veículos ativos
    public static List<EntradaVeiculo> getVeiculosAtivos() {
        return veiculosAtivos;
    }
}


