package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Feed {
    private int id;
    private String nome, instrumento, compromisso, experiencia, capa;

    public Feed(int idFeed, String nome, String instrumento, String compromisso, String experiencia, String capa) {
        this.id = idFeed;
        this.nome = nome;
        this.instrumento = instrumento;
        this.compromisso = compromisso;
        this.experiencia = experiencia;
        this.capa = capa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
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

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }
}
