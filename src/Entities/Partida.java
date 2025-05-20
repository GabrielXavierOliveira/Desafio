package Entities;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Partida {
    private List<Jogador> jogadores;
    private List<Propriedades> propriedades;
    private int rodadaAtual;
    private final int MAX_RODADAS = 1000;
    private Random random = new Random();

    public Partida(List<Jogador> jogadores, List<Propriedades> propriedades) {
        this.jogadores = jogadores;
        this.propriedades = propriedades;
        Collections.shuffle(this.jogadores); 
        this.rodadaAtual = 0;
    }

    public Resultado jogar() {
        while (rodadaAtual < MAX_RODADAS && jogadoresAtivos() > 1) {
            for (Jogador jogador : jogadores) {
                if (!jogador.estaAtivo()) continue;

                int dado = random.nextInt(6) + 1;
                jogador.mover(dado, propriedades.size());

                Propriedades propriedadeAtual = propriedades.get(jogador.getPosicao());

                if (!propriedadeAtual.temDono()) {
                    if (jogador.getCoins() >= propriedadeAtual.getCusto() && jogador.desejaComprar(propriedadeAtual)) {
                        jogador.comprar(propriedadeAtual);
                    }
                } else if (propriedadeAtual.getDono() != jogador) {
                    jogador.pagarAluguel(propriedadeAtual);
                }

                if (jogador.estaFalido()) {
                    jogador.eliminar();
                }
            }
            rodadaAtual++;
        }

        Jogador vencedor = determinarVencedor();
        boolean porTimeout = rodadaAtual == MAX_RODADAS;

        return new Resultado(vencedor.getNome(), rodadaAtual, porTimeout);
    }

    private int jogadoresAtivos() {
        int ativos = 0;
        for (Jogador j : jogadores) {
            if (j.estaAtivo()) ativos++;
        }
        return ativos;
    }

    private Jogador determinarVencedor() {
        Jogador vencedor = null;
        int maiorSaldo = Integer.MIN_VALUE;

        for (Jogador jogador : jogadores) {
            if (jogador.estaAtivo() && jogador.getCoins() > maiorSaldo) {
                vencedor = jogador;
                maiorSaldo = jogador.getCoins();
            }
        }

        
        return vencedor;
    }
}
