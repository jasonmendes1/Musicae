package pt.ipleiria.estg.dei.musicaev1.modelos;

public class BandaHabilidade {
    private int idBanda, idHabilidade;
    private String compromisso, experiencia;

    public BandaHabilidade(int idBanda, int idHabilidade, String compromisso, String experiencia) {
        this.idBanda = idBanda;
        this.idHabilidade = idHabilidade;
        this.compromisso = compromisso;
        this.experiencia = experiencia;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public String getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(String compromisso) {
        this.compromisso = compromisso;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
