package pt.ipleiria.estg.dei.musicaev1.modelos;

public class auxFeed {
    private int id, membros, capa;
    private String nome, instrumento, compromisso, experiencia;
    private static int autoIncrementID = 1;

    public auxFeed(int membros, String nome, String instrumento, String compromisso, String experiencia, int capa) {
        this.id = autoIncrementID++;
        this.membros = membros;
        this.nome = nome;
        this.instrumento = instrumento;
        this.compromisso = compromisso;
        this.experiencia = experiencia;
        this.capa = capa;
    }

    public int getId() {
        return id;
    }

    public int getMembros() {
        return membros;
    }

    public void setMembros(int membros) {
        this.membros = membros;
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

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public static int getAutoIncrementID() {
        return autoIncrementID;
    }

    public static void setAutoIncrementID(int autoIncrementID) {
        auxFeed.autoIncrementID = autoIncrementID;
    }
}
