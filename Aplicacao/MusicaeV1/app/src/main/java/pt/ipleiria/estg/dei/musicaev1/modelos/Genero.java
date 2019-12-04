package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Genero {
    private int idGenero;
    private String nome;

    public Genero(String nome) {
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
