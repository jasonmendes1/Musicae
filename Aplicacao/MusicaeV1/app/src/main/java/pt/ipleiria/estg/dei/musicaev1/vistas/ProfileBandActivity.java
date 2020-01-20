package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileBandActivity extends AppCompatActivity {

    public static  final String ID_BANDA = "idBanda";
    public static  final String NOME_BANDA = "nomeBanda";
    private Banda banda;
    private TextView tvName, tvDescription, tvGenre, tvNumber, tvCity;
    private ImageView ivBannner;
    private int idBanda;
    private Button buttonEditar, buttonCandidatos;
    private FloatingActionButton fabProcura;
    private String url = "http://192.168.1.7/MusicaeWeb/backend/web/v1";

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_band);

        idBanda = getIntent().getIntExtra(ID_BANDA, 0);
        final String nomeBanda = getIntent().getStringExtra(NOME_BANDA);

        mQueue = Volley.newRequestQueue(this);

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvGenre = findViewById(R.id.tvGenre);
        tvNumber = findViewById(R.id.tvNumber);
        tvCity = findViewById(R.id.tvCity);
        ivBannner = findViewById(R.id.ivBannner);

        System.out.println("--> IDBanda: " + idBanda);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/bandas/perfil/" + idBanda, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject banda = response.getJSONObject(i);

                                String Nome = banda.getString("Nome");
                                String Genero = banda.getString("Genero");
                                String Localizacao = banda.getString("Localizacao");
                                String Contacto = banda.getString("Contacto");
                                String Descricao = banda.getString("Descricao");
                                String Capa = banda.getString("Capa");


                                tvName.append(Nome);
                                tvDescription.append(Genero);
                                tvGenre.append(Localizacao);
                                tvNumber.append(Contacto);
                                tvCity.append(Descricao);


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


        //---------------------------------------------------------------------------- BUTOES ------------------------------------------------------------
        buttonEditar = findViewById(R.id.btnEditarBanda);
        buttonCandidatos = findViewById(R.id.btnCandidatos);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonCandidatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBandActivity.this, CandidaturesActivity.class);
                startActivity(intent);
            }
        });

        fabProcura = findViewById(R.id.fabProcura);
        fabProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBandActivity.this, SearchBandActivity.class);
                intent.putExtra(SearchBandActivity.NOME_BANDA, nomeBanda);
                startActivity(intent);
            }
        });
    }
}
