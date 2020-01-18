package pt.ipleiria.estg.dei.musicaev1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;

public class FeedJsonParser {
    public static ArrayList<Feed> parserJsonFeed(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA FEED: "+ response);
        ArrayList<Feed> tempListaFeed = new ArrayList<Feed>();

        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject feed = (JSONObject) response.get(i);

                int idFeed = feed.getInt("id");
                String nome = feed.getString("nome");
                String instrumento = feed.getString("instrumento");
                String compromisso = feed.getString("compromisso");
                String experiencia = feed.getString("experiencia");
                String capa = feed.getString("capa");

                Feed auxfeed = new Feed(idFeed, nome, instrumento, compromisso, experiencia, capa);
                tempListaFeed.add(auxfeed);
            }

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return tempListaFeed;
    }

    public static Feed parserJsonFeed(String response, Context context){
        System.out.println("--> PARSER ADICIONAR: "+ response);
        Feed auxFeed = null;

        try{
            JSONObject feed = new JSONObject(response);

            int idFeed = feed.getInt("id");
            String nome = feed.getString("nome");
            String instrumento = feed.getString("instrumento");
            String compromisso = feed.getString("compromisso");
            String experiencia = feed.getString("experiencia");
            String capa = feed.getString("capa");

            auxFeed = new Feed(idFeed, nome, instrumento, compromisso, experiencia, capa);

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxFeed;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
