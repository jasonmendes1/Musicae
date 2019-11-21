package pt.ipleiria.estg.dei.musicaev1.modelos;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;

class Singleton {

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
    static Singleton getInstance() {
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


    private void habilidadesGerarFakeData(){
        Habilidades.add(new Habilidade("Vocalista"));
        Habilidades.add(new Habilidade("Guitarra"));
        Habilidades.add(new Habilidade("Violino"));
        Habilidades.add(new Habilidade("Baterista"));
        Habilidades.add(new Habilidade("Pianista"));
    }

    private void generosGerarFakeData(){
        Generos.add(new Genero("Rock"));
        Generos.add(new Genero("Pop"));
        Generos.add(new Genero("Jazz"));
        Generos.add(new Genero("Rap"));
        Generos.add(new Genero("Reggae"));
    }

    private void perfisGerarFakeData(){
        Perfis.add(new Perfil(R.drawable.perfil, "Pedro Lopes", "23-Nov-1998", "Marinha Meca B)", "Masculino", "Oii sou um rapaz eheh B)", "Bué experiência wi"));
        Perfis.add(new Perfil(R.drawable.perfil, "Pedro Alves", "19-Fev-2010", "Marinha Meca B)", "Masculino", "Oii sou um rapazito eheh B)", "Bué pouca experiência dred"));
        Perfis.add(new Perfil(R.drawable.perfil, "Jason Mendes", "18-Fev-1657", "Leiria Meca B)", "Masculino", "Oii sou um rapazote eheh B)", "Bué pouca experiência wi"));
    }

}
