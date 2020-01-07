package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Banda {
    private int id;
    private String nome, genero, localizacao, contacto, descricao;

    public Banda(int id, String nome, String genero, String localizacao, String contacto, String descricao) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.localizacao = localizacao;
        this.contacto = contacto;
        this.descricao = descricao;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
