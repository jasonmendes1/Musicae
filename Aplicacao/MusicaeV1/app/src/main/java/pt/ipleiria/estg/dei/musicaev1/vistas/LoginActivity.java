package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.sql.SQLOutput;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.listeners.LoginListener;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;


public class LoginActivity extends AppCompatActivity implements LoginListener {


    private Perfil perfil;
    private TextInputLayout textInputUsername, textInputPassword;
    private TextInputEditText editTextUsername, editTextPassword;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
        editTextUsername = findViewById(R.id.etUsername);
        editTextPassword = findViewById(R.id.etPassword);
    }

    public void onClickLogin(View view) {
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim();

        if(!isChecked){
            descartarSharedpreferences();
        }

        Singleton.getInstance(getApplicationContext()).setLoginListener(this);
        String pw_encryptada = Singleton.getInstance(getApplicationContext()).getEncrypted(password);
        System.out.println("--> encryptado: " + pw_encryptada);
        Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(username,pw_encryptada);

/*
        int id = Singleton.getInstance().verificarLogin(username, password);
        if(id != -1){
            if(!isChecked){
                descartarSharedpreferences();
            }
            Intent intent = new Intent(this, MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
            intent.putExtra(MenuMainActivity.CHAVE_ID, ""+id);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Palavra-pass ou Username errado!", Toast.LENGTH_SHORT).show();
        }
*/

    }

    public void itemClicked(View v){
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            isChecked = true;
            guardarSharedpreferences();
        }else{
            isChecked = false;
            descartarSharedpreferences();
        }
    }

    private void guardarSharedpreferences(){
        /*
        sharedPreferences = getSharedPreferences(MenuMainActivity.SECCAO_INFO_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int id = Singleton.getInstance(getApplicationContext()).verificarLogin(editTextUsername.getText().toString().trim().toLowerCase(), editTextPassword.getText().toString().trim());
        editor.putString(MenuMainActivity.SECCAO_INFO_USER, "" + id);
        editor.apply();

         */
    }

    private void descartarSharedpreferences(){
        sharedPreferences = getSharedPreferences(MenuMainActivity.SECCAO_INFO_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(MenuMainActivity.SECCAO_INFO_USER, "-1");
        editor.apply();
    }

    @Override
    public void onRefreshLogin(String response) {
        if(response.contains("-1")){
            Toast.makeText(this, "Username ou Password Errada!", Toast.LENGTH_SHORT).show();
        }else{
            try {
                JSONObject obj = new JSONObject(response);
                System.out.println("--> objetoToString:" + obj.toString());
                System.out.println("--> phonetype value: "+ obj.getString("phonetype"));
            } catch (Throwable tx) {
                System.out.println("--> Could not parse malformed JSON: \"" + response + "\"");
            }
            Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
            System.out.println("-->" + response + "          loginActivity");
            Intent intent = new Intent(this, MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.CHAVE_USERNAME, "Pedro");
            intent.putExtra(MenuMainActivity.CHAVE_ID, ""+ "1");
            startActivity(intent);
            finish();
        }
    }

    /*
    private boolean validateUsername() {
        String usernameInput = editTextUsername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Campo não pode estar vazio");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username demasiado longo!");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Campo não pode estar vazio");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }
    */
}