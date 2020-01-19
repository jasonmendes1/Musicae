package pt.ipleiria.estg.dei.musicaev1.modelos;

public class BandaHabilidade {
    private int id;
    private String DataEntrada, BandaNome, HabilidadeNome;

    public BandaHabilidade(int id, String dataEntrada, String bandaNome, String habilidadeNome) {
        this.id = id;
        DataEntrada = dataEntrada;
        BandaNome = bandaNome;
        HabilidadeNome = habilidadeNome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDataEntrada() {
        return DataEntrada;
    }

    public String getBandaNome() {
        return BandaNome;
    }

    public String getHabilidadeNome() {
        return HabilidadeNome;
    }

    public void setDataEntrada(String dataEntrada) {
        DataEntrada = dataEntrada;
    }

    public void setBandaNome(String bandaNome) {
        BandaNome = bandaNome;
    }

    public void setHabilidadeNome(String habilidadeNome) {
        HabilidadeNome = habilidadeNome;
    }
}
