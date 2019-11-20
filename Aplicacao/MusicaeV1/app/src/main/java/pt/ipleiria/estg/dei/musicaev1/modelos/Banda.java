package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Banda {
    private int idbanda, logo;
    private String nome, descricao, localizacao, contacto;
    private static int autoIncrementID = 1;

    public Banda(String nome, String descricao, String contacto)
    {
        this.idbanda = autoIncrementID++;
}
