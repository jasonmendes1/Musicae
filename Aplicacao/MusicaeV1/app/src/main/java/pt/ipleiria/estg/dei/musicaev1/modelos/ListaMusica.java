package pt.ipleiria.estg.dei.musicaev1.modelos;

public class ListaMusica {
    private int idlistamusica;
    private String nome;

    public ListaMusica(int idlistamusica, String nome) {
        this.idlistamusica = idlistamusica;
        this.nome = nome;
    }

    public int getIdlistamusica() {
        return idlistamusica;
    }

    public void setIdlistamusica(int idlistamusica) {
        this.idlistamusica = idlistamusica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

