package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.musicaev1.R;

public class PaserActivity extends AppCompatActivity {
    private TextView mTextViewNome, mTextViewGenero, mTextViewLocal, mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paser);

        mTextViewResult = findViewById(R.id.text_view_result);
        mTextViewNome = findViewById(R.id.text_view_nome);
        mTextViewGenero = findViewById(R.id.text_view_genero);
        mTextViewLocal = findViewById(R.id.text_view_local);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    /*private void jsonParse() {

        String url = "http://192.168.1.7/MusicaeWeb/backend/web/v1/bandas/perfil/3";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject habilidade = response.getJSONObject(i);

                                String Nome = habilidade.getString("Nome");
                                String Genero = habilidade.getString("Genero");
                                String Localizacao = habilidade.getString("Localizacao");

                                mTextViewNome.append(Nome);
                                mTextViewGenero.append(Genero);
                                mTextViewLocal.append(Localizacao);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }*/

    private void jsonParse() {

        String url = "http://192.168.1.7/MusicaeWeb/backend/web/v1/bandas/perfil/3";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);

                                String Nome = obj.getString("Nome");

                                mTextViewResult.append(Nome + "\n\n");
                                System.out.println("--> OBJ" + obj.toString());
                                System.out.println("--> RESPONSE" + response.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> GET ERROR: " + error.getMessage());
            }
        });

        mQueue.add(request);
    }
}
