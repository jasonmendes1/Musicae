package pt.ipleiria.estg.dei.musicaev1.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;


public class BandaHabilidadeJsonParser {


    public static ArrayList<BandaHabilidade> parserJsonBandaHabilidade(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA BANDA HABILIDADE: "+ response);
        ArrayList<BandaHabilidade> tempListaBH = new ArrayList<BandaHabilidade>();
        //int id, String dataEntrada, String bandaNome, String habilidadeNome
        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject BandaHabilidade = (JSONObject) response.get(i);

                int idBandaHabilidade = BandaHabilidade.getInt("Id");
                String DataEntrada   = BandaHabilidade.getString("DataEntrada");
                String BandaNome = BandaHabilidade.getString("BandaNome");
                String HabilidadeNome = BandaHabilidade.getString("HabilidadeNome");

                BandaHabilidade auxfeed = new BandaHabilidade(idBandaHabilidade, DataEntrada, BandaNome, HabilidadeNome);
                tempListaBH.add(auxfeed);
            }

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        System.out.println("--> PARSER LISTA TEMP: "+ tempListaBH);
        return tempListaBH;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
