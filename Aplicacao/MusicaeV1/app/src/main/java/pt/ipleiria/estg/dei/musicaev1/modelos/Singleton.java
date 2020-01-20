package pt.ipleiria.estg.dei.musicaev1.modelos;


import android.app.Application;
import android.content.Context;

import android.util.Base64;
import android.widget.Toast;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.musicaev1.listeners.BandaHabilidadeListener;
import pt.ipleiria.estg.dei.musicaev1.listeners.BandasListener;
import pt.ipleiria.estg.dei.musicaev1.listeners.FeedListener;
import pt.ipleiria.estg.dei.musicaev1.listeners.LoginListener;
import pt.ipleiria.estg.dei.musicaev1.utils.BandaHabilidadeJsonParser;
import pt.ipleiria.estg.dei.musicaev1.utils.BandaJsonParser;
import pt.ipleiria.estg.dei.musicaev1.utils.FeedJsonParser;

public class Singleton extends Application implements FeedListener {
    private ArrayList<Banda> bandas;
    private ArrayList<Feed> bandasFeed;
    private ArrayList<BandaHabilidade> minhasBandas;
    private ArrayList<BandaMembro> BandaMembros;
    private ArrayList<Genero> Generos;
    private ArrayList<Habilidade> habilidades;
    private ArrayList<ListaFoto> listaFotos;
    private ArrayList<ListaMusica> listaMusicas;
    private ArrayList<Musico> Musicos;
    private ArrayList<Perfil> Perfis;

    private LoginListener loginListener;
    private BandaHabilidadeListener bandaHabilidadeListener;
    public int IdUser = 4;
    private int idBanda;

    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();

    private static RequestQueue volleyQueue = null;
    private String tokenAPI = "";
    private String UrlAPI = "http://192.168.1.7/MusicaeWeb/backend/web/v1";
    //private String UrlAPI = "http://192.168.1.7/MusicaeWeb/backend/web/v1";

    private MusicaeBDHelper musicaeBDHelper = null;
    private FeedListener feedListener;
    private BandasListener bandasListener;


    private static Singleton INSTANCE = null;

    public static synchronized Singleton getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private Singleton(Context context) {
        bandas = new ArrayList<>();
        bandasFeed = new ArrayList<>();
        minhasBandas = new ArrayList<>();
        BandaMembros = new ArrayList<>();
        Generos = new ArrayList<>();
        habilidades = new ArrayList<>();
        listaFotos = new ArrayList<>();
        listaMusicas = new ArrayList<>();
        Musicos = new ArrayList<>();
        Perfis = new ArrayList<>();

        musicaeBDHelper = new MusicaeBDHelper(context);
        perfisGerarFakeData();
    }

    // Gets todos:
    public ArrayList<BandaMembro> getBandaMembros() {
        return BandaMembros;
    }

    public ArrayList<Genero> getGeneros() {
        return Generos;
    }

    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
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

    public void verificaLoginAPI_POST(final String username, final String password){

        System.out.println("--> url:" + UrlAPI + "/user/verificaLogin?username="+ username +"&password_hash="+ password);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, UrlAPI + "/user/verificaLogin?username="+ username +"&password_hash="+ password, null, new Response.Listener<JSONObject>() {
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

    public void getBandasPerfilAPI (final Context context, boolean isConnected){
        System.out.println("-->wi response: BEM VINDO PUTA");
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, UrlAPI + "/bandas/membros/" + IdUser, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                minhasBandas = BandaHabilidadeJsonParser.parserJsonBandaHabilidade(response, context);
                if (bandaHabilidadeListener != null) {
                    bandaHabilidadeListener.onRefreshBandaHabilidades(minhasBandas);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO WI: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }


    public int getIdUser(){
        return IdUser;
    }

    public void setIdUser(int idUser){
        this.IdUser = idUser;
    }

    private void perfisGerarFakeData(){
        Perfis.add(new Perfil(1, "Pedro Lopes", "Masculino", "23-11-1998", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(2, "Pedro Alves", "Masculino", "01-01-2000", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(3, "Pedro Lopes", "Masculino", "01-01-1998", "Descricao bla", "foto.url", "Leiria", 9100000));
    }

    public String[] getHabilidadesFiltro() {
        //TODO: FAZER FOREACH PARA CONSTRUIR A STRING
        String[] listItems = {"Vocalist", "Guitar", "Violin", "Drums", "DJ", "Piano", "Trumpet", "Saxophone", "Flute", "Clarinet"};
        return listItems;

        /*ArrayList<String> listItems = new ArrayList<>();
        listItems.add("Vocalist");
        listItems.add("Guitar");
        listItems.add("Violin");
        listItems.add("Drums");
        listItems.add("DJ");
        listItems.add("Piano");
        listItems.add("Trumpet");
        listItems.add("Saxophone");
        listItems.add("Flute");
        listItems.add("Clarinet");
        return listItems;*/

    }

    public String[] getGeneroFiltro() {
        String[] listItems = {"Rock", "Pop", "Jazz", "Rap", "Reggae", "Acoustic", "Gospel", "Country", "Dubstep", "Metal"};
        return listItems;
    }
    //------------------------------------------------------------------ BANDAS FEED -----------------------------------------------------------------------

    public Banda getBanda(long idBanda){
        for(Banda b: bandas){
            if(b.getId() == idBanda){
                return b;
            }
        }
        return null;
    }

    public Banda getBandasBD(long idBanda){
        for(Banda l: bandas){
            if(l.getId() == idBanda){
                return l;
            }
        }
        return null;
    }

    public void adicionarBandaBD(Banda banda){
        musicaeBDHelper.adicionarBandaBD(banda);
    }

    public void adicionarBandasBD(ArrayList<Banda> listaBandas){
        musicaeBDHelper.removerAllBandaBD();
        for (Banda banda: listaBandas) {
            adicionarBandaBD(banda);
        }
    }

    public void removerBanda(int idBanda){
        Banda auxBanda = getBanda(idBanda);

        if(auxBanda != null){
            if(musicaeBDHelper.removerBandaBD(auxBanda.getId())){
                bandas.remove(auxBanda);
                System.out.println("--> BANDA removido da BD");
            }

        }
    }

    public void guardarBandaBD(Banda banda){
        if(!bandas.contains(banda)){
            return;
        }
        Banda auxBanda = getBandasBD(banda.getId());
        auxBanda.setNome(banda.getNome());
        auxBanda.setNome(banda.getNome());
        auxBanda.setDescricao(banda.getDescricao());
        auxBanda.setLocalizacao(banda.getLocalizacao());
        auxBanda.setContacto(banda.getContacto());
        auxBanda.setCapa(banda.getCapa());

        if(musicaeBDHelper.guardarBandaBD(auxBanda)){
            System.out.println("--> LIVRO ATUALIZADO NA BD");
        }
    }

    public void getAllBandasBD(final Context context, boolean isConnected){
        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT);

        if (!isConnected){
            bandas = musicaeBDHelper.getAllBandasBD();
            if (bandasListener != null){
                bandasListener.onRefreshBanda(bandas);
            }
        }else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, UrlAPI, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    bandas = BandaJsonParser.parserJsonBanda(response, context);
                    System.out.println("--> RESPOSTA: " + response);

                    adicionarBandasBD(bandas);

                    if(bandasListener != null){
                        bandasListener.onRefreshBanda(bandas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: GETALLLIVROSAPI: " + error.getMessage());
                }
            });

            volleyQueue.add(req);
        }
    }



    public void getAllBandasFeedAPI(final Context context, boolean isConnected){
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, UrlAPI + "/banda-habilidades/feed", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    bandasFeed = FeedJsonParser.parserJsonFeed(response, context);
                    System.out.println("--> RESPOSTA GET FEED API: " + bandasFeed);

                    if(feedListener != null){
                        feedListener.onRefreshListaBandasFeed(bandasFeed);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO GET FEED API: "+ error.getMessage());
                }
            });
            volleyQueue.add(req);
    }

    public void adicionarBandaFeedAPI(final Feed feed, final Context context){
        StringRequest req = new StringRequest(Request.Method.POST, UrlAPI + "/banda-habilidades/feeed/" + feed.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> RESPOSTA ADD POST: " + response);

                if(feedListener != null){
                    feedListener.onUpdateListaBandasFeed(FeedJsonParser.parserJsonFeed(response, context), 1);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO ADICIONAR BADA FEED API: "+ error.getMessage());
            }
        }) {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("nome", feed.getNome());
                params.put("instrumento", feed.getInstrumento());
                params.put("compromisso", feed.getCompromisso());
                params.put("experiencia", feed.getExperiencia());
                params.put("capa", feed.getLogo());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void editarBandaFeedAPI(final Feed feed, final Context context){
        StringRequest req = new StringRequest(Request.Method.PUT, UrlAPI + "/banda-habilidades/feeed/" + feed.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> RESPOSTA EDITAR: " + response);

                if(feedListener != null){
                    feedListener.onUpdateListaBandasFeed(FeedJsonParser.parserJsonFeed(response, context), 2);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO EDITAR LIVRO API: "+ error.getMessage());
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nome", feed.getNome());
                params.put("instrumento", feed.getInstrumento());
                params.put("compromisso", feed.getCompromisso());
                params.put("experiencia", feed.getExperiencia());
                params.put("capa", feed.getLogo());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void setFeedListener(FeedListener feedListener){
        this.feedListener = feedListener;
    }

    public void setBandaHabilidadeListener(BandaHabilidadeListener bandaHabilidadeListener){
        this.bandaHabilidadeListener = bandaHabilidadeListener;
    }

    @Override
    public void onRefreshListaBandasFeed(ArrayList<Feed> listaFeed) {

    }

    @Override
    public void onUpdateListaBandasFeed(Feed feed, int operacao) {

    }
}