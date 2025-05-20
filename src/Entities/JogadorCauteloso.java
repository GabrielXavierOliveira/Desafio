package Entities;

public class JogadorCauteloso extends Jogador {
    public JogadorCauteloso() {
        super("Cauteloso");
    }

    @Override
    public boolean desejaComprar(Propriedades p) {
        return (getCoins() - p.getCusto()) >= 80;
    }
}