package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Banda {
    private int id, contacto;
    private String nome, idgenero, localizacao, descricao, logo, removida;

    public Banda(int id,String nome,  String descricao, String localizacao, int contacto, String Logo, String removida, String idgenero) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.contacto = contacto;
        this.descricao = descricao;
        this.logo = Logo;
        this.removida = removida;
        this.idgenero = idgenero;
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

    public String getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(String idgenero) {
        this.idgenero = idgenero;
    }

    public String getRemovida() {
        return removida;
    }

    public void setRemovida(String removida) {
        this.removida = removida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return idgenero;
    }

    public void setGenero(String genero) {
        this.idgenero = genero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapa() {
        return logo;
    }

    public void setCapa(String capa) {
        this.logo = capa;
    }
}
