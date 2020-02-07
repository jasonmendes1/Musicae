package pt.ipleiria.estg.dei.musicaev1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;

public class BandaJsonParser {

    public static ArrayList<Banda> parserJsonBanda(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA BANDA: "+ response);
        ArrayList<Banda> tempListaBandas = new ArrayList<Banda>();

        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject banda = (JSONObject) response.get(i);

                int idBanda = banda.getInt("Id");
                String nome = banda.getString("Nome");
                String genero = banda.getString("Genero");
                String descricao = banda.getString("Descricao");
                String localizacao = banda.getString("Localizacao");
                Integer contacto = banda.getInt("Contacto");
                String logo = banda.getString("Logo");
                String removida = banda.getString("Removida");
                String idGenero = banda.getString("IdGenero");

                Banda auxbanda = new Banda(idBanda, nome, descricao, localizacao, contacto, logo, removida, idGenero);
                tempListaBandas.add(auxbanda);
            }

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        System.out.println("--> PARSER LISTA TEMP: "+ tempListaBandas);
        return tempListaBandas;
    }

    public static Banda parserJsonBanda(String response, Context context){
        System.out.println("--> PARSER ADICIONAR: "+ response);
        Banda auxBanda = null;

        try{
            JSONObject banda = new JSONObject(response);

            int idBanda = banda.getInt("Id");
            String nome = banda.getString("Nome");
            String genero = banda.getString("Genero");
            String descricao = banda.getString("Descricao");
            String localizacao = banda.getString("Localizacao");
            Integer contacto = banda.getInt("Contacto");
            String logo = banda.getString("Logo");
            String removida = banda.getString("Removida");
            String idGenero = banda.getString("IdGenero");

            auxBanda = new Banda(idBanda, nome, descricao, localizacao, contacto, logo, removida, idGenero);

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxBanda;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
