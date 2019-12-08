package pt.ipleiria.estg.dei.musicaev1.modelos;

public class MusicoGenero{
    private int id;
    private int idMusico;
    private String nome;

    public MusicoGenero(int idMusico,String nome){
        this.idMusico = idMusico;
        this.nome = nome;
    }

    public int getIdMusico() {
        return idMusico;
    }

    public String getNome() {
        return nome;
    }
}
/*

package pt.ipleiria.estg.dei.musicaev1.modelos;

public class MusicoHabilidade {
    private String experiencia;

    public MusicoHabilidade(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}


 */
