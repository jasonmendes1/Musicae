package pt.ipleiria.estg.dei.musicaev1.modelos;


import android.content.Context;
import android.graphics.PathEffect;

import android.util.Base64;

import java.net.UnknownServiceException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.listeners.LoginListener;

public class Singleton {
    private ArrayList<Banda> Bandas;
    private ArrayList<FeedModel> bandasFeed;
    private ArrayList<BandaMembro> BandaMembros;
    private ArrayList<Genero> Generos;
    private ArrayList<Habilidade> Habilidades;
    private ArrayList<Industria> Industrias;
    private ArrayList<ListaFoto> listaFotos;
    private ArrayList<ListaMusica> listaMusicas;
    private ArrayList<Musico> Musicos;
    private ArrayList<Perfil> Perfis;

    private LoginListener loginListener;


    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();

    private static RequestQueue volleyQueue = null;
    private String tokenAPI = "";
    //Metam aqui o vosso IPV4 para testar o login pela api
    private String UrlAPILivros = "http://192.168.1.68/MusicaeWeb/backend/web/v1";

    private boolean aux = false;

    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance(Context context) {
        volleyQueue = Volley.newRequestQueue(context);
        return ourInstance;
    }

    private Singleton() {
        Bandas = new ArrayList<>();
        bandasFeed = new ArrayList<>();
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
        industriasGerarFakeData();
        bandasGerarFakeData();
    }

    // Gets todos:
    public ArrayList<Banda> getBandas() {
        return Bandas;
    }

    public ArrayList<BandaMembro> getBandaMembros() {
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
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }


    public String getEncrypted(String plainText) {
        if (plainText == null) {
            return null;
        }
        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encodedValue,Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Failed to encrypt data");
    }

    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }


    public void setLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public void verificaLoginAPI_POST(final String username,final String password){
        //System.out.println("--> url: >" + UrlAPILivros + "/user/"+ username + "/" + password + "<");
        System.out.println("--> url: >"+UrlAPILivros + "/user/verificaLogin <");

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, UrlAPILivros + "/user/verificaLogin?username="+ username +"&password_hash="+ password, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(loginListener!=null){
                    loginListener.onRefreshLogin(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> Erro: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    private void habilidadesGerarFakeData() {
        Habilidades.add(new Habilidade(1, "Vocalist"));
        Habilidades.add(new Habilidade(2, "Guitar"));
        Habilidades.add(new Habilidade(3, "Violin"));
        Habilidades.add(new Habilidade(4, "Drums"));
        Habilidades.add(new Habilidade(5, "DJ"));
        Habilidades.add(new Habilidade(6, "Piano"));
        Habilidades.add(new Habilidade(7, "Trumpet"));
        Habilidades.add(new Habilidade(8, "Saxophone"));
        Habilidades.add(new Habilidade(9, "Flute"));
        Habilidades.add(new Habilidade(10, "Clarinet"));
    }

    private void generosGerarFakeData() {
        Generos.add(new Genero(1, "Rock"));
        Generos.add(new Genero(2, "Pop"));
        Generos.add(new Genero(3, "Jazz"));
        Generos.add(new Genero(4, "Rap"));
        Generos.add(new Genero(5, "Reggae"));
        Generos.add(new Genero(6, "Acoustic"));
        Generos.add(new Genero(7, "Gospel"));
        Generos.add(new Genero(8, "Country"));
        Generos.add(new Genero(9, "Dubstep"));
        Generos.add(new Genero(10, "Metal"));
    }

    private void industriasGerarFakeData() {
        Industrias.add(new Industria("Photographer"));
        Industrias.add(new Industria("Management"));
        Industrias.add(new Industria("Music Teacher"));
        Industrias.add(new Industria("Recording Studio"));
        Industrias.add(new Industria("Song Writer"));
        Industrias.add(new Industria("Other"));
    }

    private void perfisGerarFakeData(){
        Perfis.add(new Perfil(1, "Pedro Lopes", "Masculino", "23-11-1998", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(2, "Pedro Alves", "Masculino", "01-01-2000", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(3, "Pedro Lopes", "Masculino", "01-01-1998", "Descricao bla", "foto.url", "Leiria", 9100000));
    }

    public ArrayList<FeedModel> getBandasFeed() {
        return bandasFeed;
    }

    public FeedModel getBandaFeed(long idBandasFeed) {
        for (FeedModel i : bandasFeed) {
            if (i.getId() == idBandasFeed) {
                return i;
            }
        }
        return null;
    }

    public void adicionarBandaFeed(FeedModel livro) {
        bandasFeed.add(livro);
    }

    public void removerBandaFeed(int idBandasFeed) {
        FeedModel auxBandaFeed = getBandaFeed(idBandasFeed);
        if (auxBandaFeed != null) {
            bandasFeed.remove(auxBandaFeed);
        }
    }

    public void editarBandaFeed(FeedModel bandaFeed) {
        if (!bandasFeed.contains(bandaFeed)) {
            return;
        }
        FeedModel auxBandaFeed = getBandaFeed(bandaFeed.getId());
        auxBandaFeed.setNome(bandaFeed.getNome());
        auxBandaFeed.setInstrumento(bandaFeed.getInstrumento());
        auxBandaFeed.setCompromisso(bandaFeed.getCompromisso());
        auxBandaFeed.setExperiencia(bandaFeed.getExperiencia());
    }

    private void bandasGerarFakeData() {
        bandasFeed.add(new FeedModel(2, "Banda 1", "Guitarra", "diversao", "experiente", R.drawable.perfil));
        bandasFeed.add(new FeedModel(3, "Banda 2", "Piano", "tour", "amador", R.drawable.perfil));
        bandasFeed.add(new FeedModel(3, "Banda 3", "Flauta", "diversao", "experiente", R.drawable.perfil));
        bandasFeed.add(new FeedModel(1, "Banda 4", "Guitarra", "diversao", "iniciante", R.drawable.perfil));
        bandasFeed.add(new FeedModel(4, "Banda 5", "Vocalista", "diversao", "experiente", R.drawable.perfil));
    }

    public String[] getHabilidadesFiltro() {
        //TODO: FAZER FOREACH PARA CONSTRUIR A STRING
        String[] listItems = {"Vocalist", "Guitar", "Violin", "Drums", "DJ", "Piano", "Trumpet", "Saxophone", "Flute", "Clarinet"};
        return listItems;
    }

    public String[] getIndustriasFiltro() {
        String[] listItems = {"Photographer", "Management", "Music Teacher", "Recording Studio", "Song Writer", "Other"};
        return listItems;
    }

    public String[] getGeneroFiltro() {
        String[] listItems = {"Rock", "Pop", "Jazz", "Rap", "Reggae", "Acoustic", "Gospel", "Country", "Dubstep", "Metal"};
        return listItems;
    }
}