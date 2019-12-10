package pt.ipleiria.estg.dei.musicaev1.modelos;

public class BandaMembro {
    private int idBanda, idMusico, idHabilidade;
    private String dataentrada;

    public BandaMembro(int idBanda, int idMusico, int idHabilidade, String dataentrada) {
        this.idBanda = idBanda;
        this.idMusico = idMusico;
        this.idHabilidade = idHabilidade;
        this.dataentrada = dataentrada;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public int getIdMusico() {
        return idMusico;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public String getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(String dataentrada) {
        this.dataentrada = dataentrada;
    }
}
