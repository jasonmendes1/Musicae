package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Industria {

    private int idIndustria;
    private String tipo;

    public Industria(int idIndustria, String tipo){
        this.idIndustria = idIndustria;
        this.tipo = tipo;
    }

    public int getIdIndustria() {
        return idIndustria;
    }

    public String getTipo() {
        return tipo;
    }
}
