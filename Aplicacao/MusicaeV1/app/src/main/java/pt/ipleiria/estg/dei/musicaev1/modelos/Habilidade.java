package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Habilidade {
    private int idHabilidade;
    private String tipo;
    private static int autoIncrementoId = 1;


    public Habilidade(String tipo){
        this.idHabilidade = autoIncrementoId++;
        this.tipo = tipo;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public String getTipo() {
        return tipo;
    }
}
