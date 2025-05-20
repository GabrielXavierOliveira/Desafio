package Application;

import Entities.Leitor;
import Entities.Propriedades;
import Entities.Simulador;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivo = "src/gameConfig.txt";

        List<Propriedades> propriedades = Leitor.lerPropriedades(caminhoArquivo);

        if (propriedades.size() != 20) {
            System.out.println("Erro: O arquivo deve conter exatamente 20 propriedades.");
            return;
        }

        Simulador simulador = new Simulador(propriedades);
        simulador.executarSimulacoes();
    }
}
