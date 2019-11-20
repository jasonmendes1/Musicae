package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Habilidade {
    private int idHabilidade;
    private String tipo;

    public Habilidade(int idHabilidade, String tipo){
        this.idHabilidade = idHabilidade;
        this.tipo = tipo;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public String getTipo() {
        return tipo;
    }
}
