package pt.ipleiria.estg.dei.musicaev1.modelos;

public class ListaMusica {
    int idlistamusica;
    String nome;
    private static int autoIncrementID = 1;

    public ListaMusica(String nome) {
        this.idlistamusica = autoIncrementID++;
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

