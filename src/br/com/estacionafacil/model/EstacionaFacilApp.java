package br.com.estacionafacil.model;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionaFacilApp {

    private static List<EntradaVeiculo> veiculosAtivos = new ArrayList<>();

    public static EntradaVeiculo registrarEntrada(String placa, String modelo, String proprietario) throws Exception {
        for (EntradaVeiculo veiculo : veiculosAtivos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                throw new Exception("Veículo já está no estacionamento!");
            }
        }
        EntradaVeiculo veiculo = new EntradaVeiculo(placa, modelo, proprietario);
        veiculosAtivos.add(veiculo);

        return veiculo;
    }

    public static double RegistrarSaida(String placa) throws Exception {
        EntradaVeiculo veiculoEcontrado = null;

        for (EntradaVeiculo veiculo : veiculosAtivos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoEcontrado = veiculo;
                break;
            }
        }

        if (veiculoEcontrado == null) {
            throw new Exception("Veículo não encontrado");
        }

        LocalDateTime saida = LocalDateTime.now();
        veiculoEcontrado.setHoraSaida(saida);

        long minutos = Duration.between(veiculoEcontrado.getHoraEntrada(), saida).toMinutes();

        long horasPagas = 1;

        if (minutos > 60) {
            long extra = minutos - 60;
            horasPagas += extra / 60;
            if (extra % 60 >= 5) {
                horasPagas++;
            }
        }

        double valor = 10 + (horasPagas - 1) * 5;
            veiculosAtivos.remove(veiculoEcontrado);
            return valor;
        }

        public static List<EntradaVeiculo> getVeiculosAtivos(){
            return veiculosAtivos;

    }
}
