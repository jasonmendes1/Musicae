package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.etUsername);
        editTextPassword = findViewById(R.id.etPassword);
    }

    public void onClickLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        /*if(!isEmailValido(email)){
            editTextEmail.setError(getString(R.string.erro_email));
            return;
        }

        if(!isPasswordValida(password)){
            editTextPassword.setError(getString(R.string.erro_password));
            return;
        }*/

        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
        startActivity(intent);
        finish();
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
