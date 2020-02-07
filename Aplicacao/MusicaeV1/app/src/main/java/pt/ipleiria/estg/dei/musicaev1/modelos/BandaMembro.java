package pt.ipleiria.estg.dei.musicaev1.modelos;

public class BandaMembro {
    private int id, idBanda;
    private String dataentrada, bandaNome, habilidadeNome;

    public BandaMembro(int idBanda, String dataentrada, String bandaNome, String habilidadeNome) {
        this.id++;
        this.idBanda = idBanda;
        this.dataentrada = dataentrada;
        this.bandaNome = bandaNome;
        this.habilidadeNome = habilidadeNome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public String getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(String dataentrada) {
        this.dataentrada = dataentrada;
    }

    public String getBandaNome() {
        return bandaNome;
    }

    public void setBandaNome(String bandaNome) {
        this.bandaNome = bandaNome;
    }

    public String getHabilidadeNome() {
        return habilidadeNome;
    }

    public void setHabilidadeNome(String habilidadeNome) {
        this.habilidadeNome = habilidadeNome;
    }
}
