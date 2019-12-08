package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Habilidade {
    private int id;
    private String tipo;
    private static int autoIncrementoId = 1;


    public Habilidade(int id, String tipo){
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
