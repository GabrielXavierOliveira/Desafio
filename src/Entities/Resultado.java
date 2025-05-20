package Entities;
public class Resultado {
    private String nomeVencedor;
    private int totalRodadas;
    private boolean terminouPorTimeout;

    public Resultado(String nomeVencedor, int totalRodadas, boolean terminouPorTimeout) {
        this.nomeVencedor = nomeVencedor;
        this.totalRodadas = totalRodadas;
        this.terminouPorTimeout = terminouPorTimeout;
    }

    public String getNomeVencedor() {
        return nomeVencedor;
    }

    public int getTotalRodadas() {
        return totalRodadas;
    }

    public boolean isTerminouPorTimeout() {
        return terminouPorTimeout;
    }
}
