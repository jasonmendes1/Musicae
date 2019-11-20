package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = new Intent(this, MenuMainActivity.class);
        startActivity(intent);
        finish();
    }
}
