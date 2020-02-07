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
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaMembro;


public class BandaHabilidadeJsonParser {


    public static ArrayList<BandaMembro> parserJsonBandaHabilidade(JSONArray response, Context context){
        System.out.println("--> PARSER LISTA BANDA HABILIDADE: "+ response);
        ArrayList<BandaMembro> tempListaBM = new ArrayList<BandaMembro>();
        try{
            for(int i = 0; i < response.length(); i++){
                JSONObject BandaHabilidade = (JSONObject) response.get(i);

                int idBanda = BandaHabilidade.getInt("Id");
                String DataEntrada   = BandaHabilidade.getString("DataEntrada");
                String BandaNome = BandaHabilidade.getString("BandaNome");
                String HabilidadeNome = BandaHabilidade.getString("HabilidadeNome");

                BandaMembro auxBandaMembro = new BandaMembro(idBanda, DataEntrada, BandaNome, HabilidadeNome);
                tempListaBM.add(auxBandaMembro);
            }

        }catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        System.out.println("--> PARSER LISTA TEMP: "+ tempListaBM);
        return tempListaBM;
    }

    public static boolean isConnectionInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
