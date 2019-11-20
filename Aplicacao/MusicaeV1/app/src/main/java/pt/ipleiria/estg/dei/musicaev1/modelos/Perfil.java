package pt.ipleiria.estg.dei.musicaev1.modelos;

public class Perfil {
    private int idperfil, datanasc, imagem;
    private String nome, sexo, descricao, localidade, experiencia;
    private static int autoIncrementID = 1;

    public Perfil(int imagem, String nome, int datanasc, String localidade, String sexo, String descricao, String experiencia)
    {
        this.idperfil = autoIncrementID++;
        this.imagem = imagem;
        this.nome = nome;
        this.datanasc = datanasc;
        this.localidade = localidade;
        this.sexo = sexo;
        this.descricao = descricao;
        this.experiencia = experiencia;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public int getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(int datanasc) {
        this.datanasc = datanasc;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
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

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

}


