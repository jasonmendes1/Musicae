package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Banda {
    private int idbanda;
    private String logo, nome, descricao, localizacao, contacto;

    public Banda(int idbanda, String logo, String nome, String descricao, String localizacao, String contacto) {
        this.idbanda = idbanda;
        this.logo = logo;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.contacto = contacto;
    }

    public int getIdbanda() {
        return idbanda;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

}
