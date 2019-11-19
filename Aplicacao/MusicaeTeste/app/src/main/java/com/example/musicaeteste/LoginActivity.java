package com.example.musicaeteste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLOutput;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edPassword;
    private CheckBox rememberMe;

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        rememberMe = findViewById(R.id.cbRememberMe);
    }

    public void onClickLogin(View view){
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

/* Bypass na verificação do login
        if(!isEmailValido(email)){
            edEmail.setError(getString(R.string.erro_password));
            return;2
        }

        if(!isPasswordValida(password)){
            edPassword.setError(getString(R.string.erro_password));
            return;
        }
*/

        if(rememberMe.isChecked()){
            /*
            getSharedPreferences(PREFS_NAME,MODE_PRIVATE).edit().putString(PREF_EMAIL, email).putString(PREF_PASSWORD, password).commit();
             */
        }

        /*Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.CHAVE_EMAIL, email);
        startActivity(intent);
        finish();*/
        Toast.makeText(this, "oi", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "ollaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
    }


    public boolean isEmailValido(String email){
        if(email == null){
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValida(String password){
        if(password == null) {
            return false;
        }
        return password.length() > 4;
    }

}
