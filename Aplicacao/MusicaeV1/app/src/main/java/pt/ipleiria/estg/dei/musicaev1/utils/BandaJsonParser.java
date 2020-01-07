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

public class BandaJsonParser {
    public static ArrayList<Banda> parserJsonBandas(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA BANDAS: "+ response);
        ArrayList<Banda> tempListaBandas = new ArrayList<Banda>();

        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject banda = (JSONObject) response.get(i);

                int idBanda = banda.getInt("id");
                String nome = banda.getString("nome");
                String genero = banda.getString("genero");
                String localizacao = banda.getString("localizacao");
                String contacto = banda.getString("contacto");
                String descricao = banda.getString("descricao");

                Banda auxBanda = new Banda(idBanda, nome, genero, localizacao, contacto, descricao);
                tempListaBandas.add(auxBanda);
            }

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaBandas;
    }

    public static Banda parserJsonBandas(String response, Context context){
        System.out.println("--> PARSER ADICIONAR: "+ response);
        Banda auxBanda = null;

        try{
            JSONObject banda = new JSONObject(response);

            int idBanda = banda.getInt("id");
            String nome = banda.getString("nome");
            String genero = banda.getString("genero");
            String localizacao = banda.getString("localizacao");
            String contacto = banda.getString("contacto");
            String descricao = banda.getString("descricao");

            auxBanda = new Banda(idBanda, nome, genero, localizacao, contacto, descricao);

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
