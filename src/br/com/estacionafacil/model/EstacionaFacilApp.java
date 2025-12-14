package br.com.estacionafacil.model;

import br.com.estacionafacil.repository.EstacionamentoRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionaFacilApp {

    // Lista de veículos atualmente estacionados
    private static List<EntradaVeiculo> veiculosAtivos = new ArrayList<>();

    // Registra a entrada de um veículo
    public static EntradaVeiculo registrarEntrada(String placa, String modelo, String proprietario) throws Exception {

        // Verifica duplicidade
        for (EntradaVeiculo v : veiculosAtivos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                throw new Exception("Veículo já está no estacionamento!");
            }
        }

        EntradaVeiculo veiculo = new EntradaVeiculo(placa, modelo, proprietario);
        veiculosAtivos.add(veiculo);

        // Salva no CSV de veículos ativos
        EstacionamentoRepository.salvarEntrada(veiculo);

        return veiculo;
    }

    // Registra a saída do veículo
    public static double registrarSaida(String placa) throws Exception {

        EntradaVeiculo veiculoEncontrado = null;

        for (EntradaVeiculo v : veiculosAtivos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                veiculoEncontrado = v;
                break;
            }
        }

        if (veiculoEncontrado == null) {
            throw new Exception("Veículo não encontrado!");
        }

        LocalDateTime saida = LocalDateTime.now();
        veiculoEncontrado.setHoraSaida(saida);

        long minutos = Duration.between(veiculoEncontrado.getHoraEntrada(), saida).toMinutes();
        long horasPagas = 1;

        if (minutos > 60) {
            long extra = minutos - 60;
            horasPagas += extra / 60;
            if (extra % 60 >= 5) horasPagas++;
        }

        double valor = 10 + (horasPagas - 1) * 5;
        veiculoEncontrado.setValorPagar(valor);

        // Salva no histórico
        EstacionamentoRepository.salvarHistorico(veiculoEncontrado);

        // Remove do CSV de ativos
        EstacionamentoRepository.removerVeiculoAtivo(placa);

        // Remove da lista em memória
        veiculosAtivos.remove(veiculoEncontrado);

        return valor;
    }

    public static List<EntradaVeiculo> getVeiculosAtivos() {
        return veiculosAtivos;
    }
}



