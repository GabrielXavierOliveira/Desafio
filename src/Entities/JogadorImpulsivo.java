package Entities;

public class JogadorImpulsivo extends Jogador {
    public JogadorImpulsivo() {
        super("Impulsivo");
    }

    @Override
    public boolean desejaComprar(Propriedades p) {
        return true;
    }
}
