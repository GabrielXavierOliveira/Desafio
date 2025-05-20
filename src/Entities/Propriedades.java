package Entities;

public class Propriedades {
    private int custo;
    private int aluguel;
    private Jogador dono;

    public Propriedades(int custo, int aluguel) {
        this.custo = custo;
        this.aluguel = aluguel;
        this.dono = null;
    }

    public int getCusto() { return custo; }
    public int getAluguel() { return aluguel; }
    public Jogador getDono() { return dono; }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public boolean temDono() {
        return dono != null;
    }

    public void resetarDono() {
        dono = null;
    }
}
