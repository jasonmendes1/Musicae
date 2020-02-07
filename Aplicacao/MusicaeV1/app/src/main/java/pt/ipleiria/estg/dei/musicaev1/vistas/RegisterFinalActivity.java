package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterFinalActivity extends AppCompatActivity {
    public static  final String ID_HABILIDADE = "idHabilidade";
    public static  final String ID_GENERO = "idGenero";
    public static  final String PROFILE_NOME = "profileNome";
    public static  final String PROFILE_SEXO = "profileSexo";
    public static  final String USER_EMAIL = "userEmail";
    public static  final String PROFILE_LOCALIDADE = "profileLocalidade";
    public static  final String PROFILE_DATANASC = "profileDataNasc";




    private Button buttonRegistar;
    private String urlAPI, ipURL;
    private RequestQueue mQueue;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_final);
        mQueue = Volley.newRequestQueue(getApplicationContext());

        etUsername = findViewById(R.id.etUsernameRegister);
        etPassword = findViewById(R.id.etPasswordRegistar);


        System.out.println("--> Dados todos: IdHabilidade=" + getIntent().getIntExtra(ID_HABILIDADE, 0)
        +  " IdGenero=" + getIntent().getIntExtra(ID_GENERO, 0)
        +  " profileNome=" + getIntent().getStringExtra(PROFILE_NOME)
        + " profileSexo=" + getIntent().getStringExtra(PROFILE_SEXO)
                + " userEmail=" + getIntent().getStringExtra(USER_EMAIL)
                + " profileLocalidade" + getIntent().getStringExtra(PROFILE_LOCALIDADE)
                + " profileDataNasc" + getIntent().getStringExtra(PROFILE_DATANASC)
        );

        buttonRegistar = findViewById(R.id.btnRegister);
        buttonRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipURL = Singleton.getInstance(getApplicationContext()).getIp();
                urlAPI = "http://" + ipURL + "/MusicaeWeb/backend/web/v1/user";
                Intent intent = new Intent(RegisterFinalActivity.this, LoginActivity.class);

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, urlAPI + "/add?userUsername=" + etUsername.getText().toString()
                        + "&userPassword=" +  etPassword.getText().toString() + "&userEmail=" + getIntent().getStringExtra(USER_EMAIL)
                        + "&profileNome=" + getIntent().getStringExtra(PROFILE_NOME)
                        + "&profileSexo=" + getIntent().getStringExtra(PROFILE_SEXO)
                        + "&profileLocalidade=" + getIntent().getStringExtra(PROFILE_LOCALIDADE)
                        + "&profileDataNasc=" + getIntent().getStringExtra(PROFILE_DATANASC)
                        + "&musicoIdHabilidade=" + getIntent().getIntExtra(ID_HABILIDADE, 0)
                        + "&musicoIdGenero=" + getIntent().getIntExtra(ID_GENERO, 0), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println("--> Eroor: " + error.getMessage());
                    }
                });
                mQueue.add(request);

                startActivity(intent);
            }
        });
    }
}
