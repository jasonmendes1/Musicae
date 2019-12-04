package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Perfil {
    private int idperfil;
    private String imagem, username, password, nome, sexo, descricao, localidade, experiencia, datanasc;

    public Perfil(int idperfil, String imagem, String username, String password, String nome,  String datanasc, String localidade, String sexo, String descricao) {
        this.idperfil = idperfil;
        this.imagem = imagem;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.datanasc = datanasc;
        this.localidade = localidade;
        this.sexo = sexo;
        this.descricao = descricao;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setId(int anInt) {
    }
}


