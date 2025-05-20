package Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Jogador {
    private String nome;
    private int coins = 300;
    private int posicao = 0;
    private boolean ativo = true;
    private List<Propriedades> propriedades = new ArrayList<>();

    public Jogador(String nome) {
        this.nome = nome;
    }

    public abstract boolean desejaComprar(Propriedades propriedade);

    public void mover(int passos, int totalPropriedades) {
        int novaPosicao = posicao + passos;
        if (novaPosicao >= totalPropriedades) {
            coins += 100;
        }
        posicao = novaPosicao % totalPropriedades;
    }

    public void comprar(Propriedades p) {
        coins -= p.getCusto();
        p.setDono(this);
        propriedades.add(p);
    }

    public void pagarAluguel(Propriedades p) {
        Jogador dono = p.getDono();
        int aluguel = p.getAluguel();
        coins -= aluguel;
        dono.receber(aluguel);
    }

    public void receber(int valor) {
        coins += valor;
    }

    public boolean estaFalido() {
        return coins < 0;
    }

    public void eliminar() {
        ativo = false;
        for (Propriedades p : propriedades) {
            p.resetarDono();
        }
        propriedades.clear();
    }

    public String getNome() { return nome; }
    public int getPosicao() { return posicao; }
    public int getCoins() { return coins; }
    public boolean estaAtivo() { return ativo; }
}
