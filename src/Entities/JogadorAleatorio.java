package Entities;

import java.util.Random;

public class JogadorAleatorio extends Jogador {
    private Random random = new Random();

    public JogadorAleatorio() {
        super("Aleatório");
    }

    @Override
    public boolean desejaComprar(Propriedades p) {
        return random.nextBoolean();
    }
}
