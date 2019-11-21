package pt.ipleiria.estg.dei.musicaev1.modelos;

import java.sql.Time;

public class IndustriaBanda {
    private Time duracao;

    public IndustriaBanda(Time duracao) {
        this.duracao = duracao;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }
}
