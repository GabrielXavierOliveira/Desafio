package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulador {
    private List<Propriedades> propriedades;
    private int totalSimulacoes = 300;

    
    private int partidasTimeout = 0;
    private int somaRodadas = 0;
    private Map<String, Integer> vitoriasPorJogador = new HashMap<>();

    public Simulador(List<Propriedades> propriedades) {
        this.propriedades = propriedades;

        
        vitoriasPorJogador.put("Impulsivo", 0);
        vitoriasPorJogador.put("Exigente", 0);
        vitoriasPorJogador.put("Cauteloso", 0);
        vitoriasPorJogador.put("Aleatório", 0);
    }

    public void executarSimulacoes() {
        for (int i = 0; i < totalSimulacoes; i++) {
            List<Jogador> jogadores = criarJogadores();
            List<Propriedades> propriedadesCopia = copiarPropriedades();

            Partida partida = new Partida(jogadores, propriedadesCopia);
            Resultado resultado = partida.jogar();

            somaRodadas += resultado.getTotalRodadas();

            if (resultado.isTerminouPorTimeout()) {
                partidasTimeout++;
            }

            String vencedor = resultado.getNomeVencedor();
            vitoriasPorJogador.put(vencedor, vitoriasPorJogador.get(vencedor) + 1);
        }

        imprimirRelatorio();
    }

    private List<Jogador> criarJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new JogadorImpulsivo());
        jogadores.add(new JogadorExigente());
        jogadores.add(new JogadorCauteloso());
        jogadores.add(new JogadorAleatorio());
        return jogadores;
    }

    private List<Propriedades> copiarPropriedades() {
        List<Propriedades> copia = new ArrayList<>();
        for (Propriedades p : propriedades) {
            copia.add(new Propriedades(p.getCusto(), p.getAluguel()));
        }
        return copia;
    }

    private void imprimirRelatorio() {
        System.out.println("===== RELATÓRIO DE SIMULAÇÕES =====");
        System.out.println("Total de partidas: " + totalSimulacoes);
        System.out.println("Partidas que terminaram por timeout (1000 rodadas): " + partidasTimeout);
        System.out.printf("Média de rodadas por partida: %.2f\n", (double) somaRodadas / totalSimulacoes);

        System.out.println("\nVITÓRIAS POR TIPO DE JOGADOR:");
        for (Map.Entry<String, Integer> entry : vitoriasPorJogador.entrySet()) {
            double percentual = (entry.getValue() * 100.0) / totalSimulacoes;
            System.out.printf("%s: %d (%.2f%%)\n", entry.getKey(), entry.getValue(), percentual);
        }

        String comportamentoMaisVencedor = determinarMaisVencedor();
        System.out.println("\nComportamento que mais venceu: " + comportamentoMaisVencedor);
    }

    private String determinarMaisVencedor() {
        String maisVencedor = null;
        int maiorVitorias = 0;

        for (Map.Entry<String, Integer> entry : vitoriasPorJogador.entrySet()) {
            if (entry.getValue() > maiorVitorias) {
                maiorVitorias = entry.getValue();
                maisVencedor = entry.getKey();
            }
        }
        return maisVencedor;
    }
}
