package pt.ipleiria.estg.dei.musicaev1.modelos;

public class CandidaturaModel {
    private int id, capa;
    private String nome, instrumento;
    private static int autoIncrementID = 1;

    public CandidaturaModel(String nome, String instrumento, int capa) {
        this.id = autoIncrementID++;
        this.nome = nome;
        this.instrumento = instrumento;
        this.capa = capa;
    }

    public int getId() {
        return id;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
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

    public static int getAutoIncrementID() {
        return autoIncrementID;
    }

    public static void setAutoIncrementID(int autoIncrementID) {
        Candidatura.autoIncrementID = autoIncrementID;
    }
}
