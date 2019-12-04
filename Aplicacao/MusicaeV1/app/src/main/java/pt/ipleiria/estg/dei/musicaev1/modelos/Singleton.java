package pt.ipleiria.estg.dei.musicaev1.modelos;


import java.util.ArrayList;

public class Singleton {
    private ArrayList<Banda>Bandas;
    private ArrayList<BandaMembros>BandaMembros;
    private ArrayList<Genero>Generos;
    private ArrayList<Habilidade>Habilidades;
    private ArrayList<Industria>Industrias;
    private ArrayList<ListaFoto>listaFotos;
    private ArrayList<ListaMusica>listaMusicas;
    private ArrayList<Musico>Musicos;
    private ArrayList<Perfil>Perfis;

    private static final Singleton ourInstance = new Singleton();
    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        Bandas = new ArrayList<>();
        BandaMembros = new ArrayList<>();
        Generos = new ArrayList<>();
        Habilidades = new ArrayList<>();
        Industrias = new ArrayList<>();
        listaFotos = new ArrayList<>();
        listaMusicas = new ArrayList<>();
        Musicos = new ArrayList<>();
        Perfis = new ArrayList<>();

        //Funções de gerar fake data
        habilidadesGerarFakeData();
        generosGerarFakeData();
        perfisGerarFakeData();
    }

    // Gets todos:
    public ArrayList<Banda> getBandas() {
        return Bandas;
    }

    public ArrayList<BandaMembros> getBandaMembros() {
        return BandaMembros;
    }

    public ArrayList<Genero> getGeneros() {
        return Generos;
    }

    public ArrayList<Habilidade> getHabilidades() {
        return Habilidades;
    }

    public ArrayList<Industria> getIndustrias() {
        return Industrias;
    }

    public ArrayList<ListaMusica> getListaMusicas() {
        return listaMusicas;
    }

    public ArrayList<Musico> getMusicos() {
        return Musicos;
    }

    public ArrayList<Perfil> getPerfis() {
        return Perfis;
    }
    // ---- End gets ----

    public Perfil getPerfil(int id){
        for (Perfil p: Perfis
             ) {
            if(p.getIdperfil() == id){
                return p;
            }
        }
        return null;
    }


    //Quando registar para verificar se o username já está a ser usado
    public boolean verificarUsername(String username){
        for (Perfil p: Perfis
        ) {
            if(p.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public int verificarLogin(String username, String password){
        for (Perfil p: Perfis
        ) {
            if(p.getUsername().equals(username) && p.getPassword().equals(password)){
                return p.getIdperfil();
            }
        }
        return -1;
    }

    private void habilidadesGerarFakeData(){
        Habilidades.add(new Habilidade("Vocalist"));
        Habilidades.add(new Habilidade("Guitar"));
        Habilidades.add(new Habilidade("Violin"));
        Habilidades.add(new Habilidade("Drumms"));
        Habilidades.add(new Habilidade("DJ"));
        Habilidades.add(new Habilidade("Piano"));
        Habilidades.add(new Habilidade("Trumpet"));
        Habilidades.add(new Habilidade("Saxophone"));
        Habilidades.add(new Habilidade("Flute"));
        Habilidades.add(new Habilidade("Clarinet"));
    }

    private void generosGerarFakeData(){
        Generos.add(new Genero("Rock"));
        Generos.add(new Genero("Pop"));
        Generos.add(new Genero("Jazz"));
        Generos.add(new Genero("Rap"));
        Generos.add(new Genero("Reggae"));
        Generos.add(new Genero("Acoustic"));
        Generos.add(new Genero("Gospel"));
        Generos.add(new Genero("Country"));
        Generos.add(new Genero("Dubstep"));
        Generos.add(new Genero("Metal"));
    }

    private void industriasGerarFakeData(){
        Industrias.add(new Industria("Photographer"));
        Industrias.add(new Industria("Management"));
        Industrias.add(new Industria("Music Teacher"));
        Industrias.add(new Industria("Recording Studio"));
        Industrias.add(new Industria("Song Writer"));
        Industrias.add(new Industria("Other"));
    }

    private void perfisGerarFakeData(){
        Perfis.add(new Perfil(1,"url","pedro","123" ,"Pedro Lopes", "23-Nov-1998", "Marinha Meca B)", "Masculino", "Oii sou um rapaz eheh B)"));
        Perfis.add(new Perfil(2,"url","pedroalves","123" , "Pedro Alves", "19-Fev-2010", "Marinha Meca B)", "Masculino", "Oii sou um rapazito eheh B)"));
        Perfis.add(new Perfil(3,"url","jason","123" , "Jason Mendes", "18-Fev-1657", "Leiria Meca B)", "Masculino", "Oii sou um rapazote eheh B)"));
    }
}
