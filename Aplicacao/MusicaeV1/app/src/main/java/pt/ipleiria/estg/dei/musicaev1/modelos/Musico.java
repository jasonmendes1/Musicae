package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Musico {
    private int idMusico;
    private static int autoIncrementoId = 1;

    public Musico(int idMusico) {
        this.idMusico = idMusico;
    }

    public int getId() {
        return idMusico;
    }
}