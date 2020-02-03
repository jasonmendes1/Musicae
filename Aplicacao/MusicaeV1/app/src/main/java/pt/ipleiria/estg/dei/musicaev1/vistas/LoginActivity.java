package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.listeners.LoginListener;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.SharedPreferencesConfig;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;


public class LoginActivity extends AppCompatActivity implements LoginListener {


    private Perfil perfil;
    private String textIP;
    private TextInputLayout textInputUsername, textInputPassword;
    private TextInputEditText editTextUsername, editTextPassword;
    private TextView textViewIP;
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
        textViewIP = findViewById(R.id.tvIP);

        textViewIP.setText(SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null));
    }

    public void onClickLogin(View view) {
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim();
        String ip = textViewIP.getText().toString().trim();

        if(!isChecked){
            descartarSharedpreferences();
        }

        //textViewIP.setText("192.168.1.7");

        if(textViewIP.getText() == ""){
            Toast.makeText(this, "Adiciona / Altera o IP!", Toast.LENGTH_SHORT).show();
        }else{
            Singleton.getInstance(getApplicationContext()).setIp(ip);
            System.out.println("--> SharedPreferences IP: " + SharedPreferencesConfig.read(SharedPreferencesConfig.IP, null));

            Singleton.getInstance(getApplicationContext()).setLoginListener(this);
            String pw_encryptada = Singleton.getInstance(getApplicationContext()).getEncrypted(password);
            System.out.println("--> encryptado: " + pw_encryptada);
            Singleton.getInstance(getApplicationContext()).verificaLoginAPI_POST(username,pw_encryptada);
        }

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
        }*/
    }

    public void onClickIP(View view) {
        dialogIP();
    }

    private void dialogIP(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        final EditText IPinput = new EditText(this);
        builder.setView(IPinput);
        IPinput.setText(textViewIP.getText());
        builder.setTitle("IP")
                .setMessage("Coloca aqui o teu IPv4")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textIP = IPinput.getText().toString();
                        textViewIP.setText(textIP);
                    }})
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }})
                //.setIcon(android.R.drawable.ic_lock_power_off)
                .show();
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
    public void onRefreshLogin(JSONArray response) {
        int iduser = 0;
        String username = null, email = null;
        for (int i = 0; i < response.length(); i++) {
            JSONObject obj = null;
            try {
                obj = response.getJSONObject(i);
                iduser = obj.getInt("id");
                username = obj.getString("username");
                email = obj.getString("email");


                if (iduser == -1) {
                    Toast.makeText(this, "Username ou Password Errada!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Singleton.getInstance(getApplicationContext()).setIdUser(iduser);
        Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
        intent.putExtra(MenuMainActivity.CHAVE_EMAIL, email);
        intent.putExtra(MenuMainActivity.CHAVE_ID, iduser+"");
        startActivity(intent);
        finish();
    }
/*
        if(response.contains("-1")){
            Toast.makeText(this, "Username ou Password Errada!", Toast.LENGTH_SHORT).show();
        }else{
            try {
                JSONArray obj = new JSONArray(response);
                int iduser = obj.getInt(0);

                Singleton.getInstance(getApplicationContext()).setIdUser(iduser);
                Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MenuMainActivity.class);
                intent.putExtra(MenuMainActivity.CHAVE_USERNAME, "Pedrosdafghj,");
                intent.putExtra(MenuMainActivity.CHAVE_ID, Singleton.getInstance(getApplicationContext()).getIdUser());
                startActivity(intent);
                finish();
            } catch (JSONException e) {
                System.out.println("--> Error: "+ e.getMessage());
            }
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