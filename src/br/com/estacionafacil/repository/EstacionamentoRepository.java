package br.com.estacionafacil.repository;

import br.com.estacionafacil.model.EntradaVeiculo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoRepository {

    private static final String BASE_PATH =
            "C:/Users//User/OneDrive/Documentos/estacionafacil";

    private static final String ATIVOS = BASE_PATH + "veiculos_estacionados.csv";
    private static final String HISTORICO = BASE_PATH + "historico_saidas.csv";

    private static final DateTimeFormatter F =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Salva entrada
    public static void salvarEntrada(EntradaVeiculo v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ATIVOS, true))) {
            bw.write(v.getPlaca() + ";" +
                    v.getModelo() + ";" +
                    v.getProprietario() + ";" +
                    v.getHoraEntrada().format(F));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove veículo ativo
    public static void removerVeiculoAtivo(String placa) {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ATIVOS))) {
            String linha;
            linhas.add(br.readLine());

            while ((linha = br.readLine()) != null) {
                if (!linha.startsWith(placa + ",")) {
                    linhas.add(linha);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ATIVOS))) {
                for (String l : linhas) {
                    bw.write(l);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salva histórico de saída
    public static void salvarHistorico(EntradaVeiculo v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORICO, true))) {
            bw.write(v.getPlaca() + ";" +
                    v.getModelo() + ";" +
                    v.getProprietario() + ";" +
                    v.getHoraEntrada().format(F) + ";" +
                    v.getHoraSaida().format(F) + ";" +
                    String.format("%.2f", v.getValorPagar()));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

