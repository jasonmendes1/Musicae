package pt.ipleiria.estg.dei.musicaev1.modelos;

public class ListaFoto {
    private int idListaFoto, foto;

    public ListaFoto(int idlistamusica, int foto) {
        this.idListaFoto = idlistamusica;
        this.foto = foto;
    }

    public int getIdListaFoto() {
        return idListaFoto;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
