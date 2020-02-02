package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Perfil {
    private int id;
    private String nome, sexo, dataNasc, descricao, foto, localidade;

    public Perfil(int id, String nome, String sexo , String dataNasc, String descricao, String foto,  String localidade) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.descricao = descricao;
        this.foto = foto;
        this.localidade = localidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getDatanasc() {
        return dataNasc;
    }

    public void setDatanasc(String datanasc) {
        this.dataNasc = datanasc;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLocalidade() {
        return localidade;
    }
}


