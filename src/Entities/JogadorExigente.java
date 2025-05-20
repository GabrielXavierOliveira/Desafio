package Entities;

public class JogadorExigente extends Jogador {
    public JogadorExigente() {
        super("Exigente");
    }

    @Override
    public boolean desejaComprar(Propriedades p) {
        return p.getAluguel() > 50;
    }
}
