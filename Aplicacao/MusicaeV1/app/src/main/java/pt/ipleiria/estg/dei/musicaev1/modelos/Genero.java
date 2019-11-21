package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Genero {
    private int idGenero;
    private String nome;
    private static int autoIncrementoId = 1;

    public Genero(String nome) {
        this.idGenero = autoIncrementoId++;
        this.nome = nome;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
