package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;


public class LoginActivity extends AppCompatActivity {

    private Perfil perfil;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;

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

        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
        startActivity(intent);
        finish();

        /*if (!validateUsername() || !validatePassword()) {
            return;
        }

        if(Singleton.getInstance().verificarLogin(username, password)){
            Intent intent = new Intent(this, MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.CHAVE_USERNAME, username);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Palavra-pass ou Username errado!", Toast.LENGTH_SHORT).show();
        }*/
    }

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
}