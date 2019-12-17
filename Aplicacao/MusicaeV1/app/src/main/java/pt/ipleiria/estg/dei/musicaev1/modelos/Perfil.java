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

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public String getLocalidade() {
        return localidade;
    }
}


