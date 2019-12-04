package pt.ipleiria.estg.dei.musicaev1.modelos;

public class ListaFoto {
    private int idListaFoto;
    private String foto;

    public ListaFoto(int idlistamusica, String foto) {
        this.idListaFoto = idlistamusica;
        this.foto = foto;
    }

    public int getIdListaFoto() {
        return idListaFoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
