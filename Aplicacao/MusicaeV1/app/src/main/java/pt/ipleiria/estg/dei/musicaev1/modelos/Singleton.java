package pt.ipleiria.estg.dei.musicaev1.modelos;


import android.app.Application;
import android.content.Context;

import android.util.Base64;
import android.widget.Toast;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.musicaev1.listeners.BandasListener;
import pt.ipleiria.estg.dei.musicaev1.listeners.LoginListener;
import pt.ipleiria.estg.dei.musicaev1.utils.BandaJsonParser;

public class Singleton extends Application implements BandasListener{
    private ArrayList<Banda> bandas;
    private ArrayList<BandaMembro> BandaMembros;
    private ArrayList<Genero> Generos;
    private ArrayList<Habilidade> Habilidades;
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
    private String UrlAPI = "http://192.168.1.7/MusicaeWeb/backend/web/v1";

    private MusicaeBDHelper musicaeBDHelper = null;
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
        BandaMembros = new ArrayList<>();
        Generos = new ArrayList<>();
        Habilidades = new ArrayList<>();
        listaFotos = new ArrayList<>();
        listaMusicas = new ArrayList<>();
        Musicos = new ArrayList<>();
        Perfis = new ArrayList<>();

        musicaeBDHelper = new MusicaeBDHelper(context);
        //Funções de gerar fake data
        habilidadesGerarFakeData();
        generosGerarFakeData();
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
        return Habilidades;
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
        //System.out.println("--> url: >" + UrlAPILivros + "/user/"+ username + "/" + password + "<");
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

    private void perfisGerarFakeData(){
        Perfis.add(new Perfil(1, "Pedro Lopes", "Masculino", "23-11-1998", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(2, "Pedro Alves", "Masculino", "01-01-2000", "Descricao bla", "foto.url", "Marinha Grande", 9100000));
        Perfis.add(new Perfil(3, "Pedro Lopes", "Masculino", "01-01-1998", "Descricao bla", "foto.url", "Leiria", 9100000));
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

    //------------------------------------------------------------------ BANDAS -----------------------------------------------------------------------

    public ArrayList<Banda> getBandasBD() {
        //return new ArrayList<>(bandas);
        bandas = musicaeBDHelper.getAllBandasBD();
        return bandas;
    }

    public Banda getBanda(long idBanda){
        for(Banda b: bandas){
            if(b.getId() == idBanda){
                return b;
            }
        }
        return null;
    }

    public void adicionarBandaBD(Banda banda){
        musicaeBDHelper.adicionarBandaBD(banda);
    }

    public void adicionarBandasBD(ArrayList<Banda> listaBandas){
        musicaeBDHelper.removerAllBandasBD();
        for (Banda banda: listaBandas) {
            adicionarBandaBD(banda);
        }
    }

    public void removerBandaBD(int idBanda){
        Banda auxBanda = getBanda(idBanda);

        if(auxBanda != null){
            if (musicaeBDHelper.removerBandaBD(auxBanda.getId())) {
                bandas.remove(auxBanda);
                System.out.println("--> Banda Removida da BD");
            }
        }
    }

    public void guardarBandaBD(Banda banda){
        if(!bandas.contains(banda)){
            return;
        }
        Banda auxBanda = getBanda(banda.getId());
        auxBanda.setNome(banda.getNome());
        auxBanda.setGenero(banda.getGenero());
        auxBanda.setLocalizacao(banda.getLocalizacao());
        auxBanda.setContacto(banda.getContacto());
        auxBanda.setDescricao(banda.getDescricao());

        if (musicaeBDHelper.guardarBandaBD(auxBanda)) {
            System.out.println("--> Banda atualizada na BD");
        }
    }


    public void getAllBandasAPI(final Context context, boolean isConnected){
        Toast.makeText(context, "isConnected", Toast.LENGTH_SHORT).show();

        if(!isConnected){
            bandas = musicaeBDHelper.getAllBandasBD();
            if(bandasListener != null){
                bandasListener.onRefreshListaBandas(bandas);
            }
        }else{
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, UrlAPI + "/bandas", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    bandas = BandaJsonParser.parserJsonBandas(response, context);
                    System.out.println("--> RESPOSTA: " + bandas);

                    adicionarBandasBD(bandas);

                    if(bandasListener != null){
                        bandasListener.onRefreshListaBandas(bandas);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: "+ error.getMessage());
                }
            });
            volleyQueue.add(req);
        }
    }

    public void adicionarBandaAPI(final Banda banda, final Context context){
        StringRequest req = new StringRequest(Request.Method.POST, UrlAPI + "/bandas", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> RESPOSTA ADD POST: " + response);

                if(bandasListener != null){
                    bandasListener.onUpdateListaBandas(BandaJsonParser.parserJsonBandas(response, context), 1);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO ADICIONAR BANDA API: "+ error.getMessage());
            }
        }) {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("token", tokenAPI);
                params.put("nome", banda.getNome());
                params.put("genero", banda.getGenero());
                params.put("localizacao", banda.getLocalizacao());
                params.put("contacto", "" + banda.getContacto());
                params.put("descricao", banda.getDescricao());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void removerBandaAPI(final Banda banda){
        StringRequest req = new StringRequest(Request.Method.DELETE, UrlAPI + "/bandas/" + banda.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> RESPOSTA REMOVER: " + response);

                if(bandasListener != null){
                    bandasListener.onUpdateListaBandas(banda, 3);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO REMOVER BANDA API: "+ error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    public void editarBandaAPI(final Banda banda, final Context context){
        StringRequest req = new StringRequest(Request.Method.PUT, UrlAPI + "/bandas/" + banda.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("--> RESPOSTA EDITAR: " + response);

                if(bandasListener != null){
                    bandasListener.onUpdateListaBandas(BandaJsonParser.parserJsonBandas(response, context), 2);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO EDITAR BANDA API: "+ error.getMessage());
            }
        }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("token", tokenAPI);
                params.put("nome", banda.getNome());
                params.put("genero", banda.getGenero());
                params.put("localizacao", banda.getLocalizacao());
                params.put("contacto", "" + banda.getContacto());
                params.put("descricao", banda.getDescricao());

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void setBandasListener(BandasListener bandasListener){
        this.bandasListener = bandasListener;
    }

    @Override
    public void onRefreshListaBandas(ArrayList<Banda> listaBandas) {

    }

    @Override
    public void onUpdateListaBandas(Banda banda, int operacao) {
        System.out.println("--> Entrou update lista bandas BD");
        switch (operacao){
            case 1: adicionarBandaBD(banda);
                break;
            case 2: guardarBandaBD(banda);
                break;
            case 3: removerBandaBD(banda.getId());
                break;

        }
    }
}