package pt.ipleiria.estg.dei.musicaev1.modelos;

public class MusicoGenero{
    private int idMusico;
    private int idHabilidade;

    public MusicoGenero(int idMusico,int idHabilidade){
        this.idMusico = idMusico;
        this.idHabilidade = idHabilidade;
    }

    public int getIdMusico() {
        return idMusico;
    }

    public int getIdHabilidade() {
        return idHabilidade;
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
