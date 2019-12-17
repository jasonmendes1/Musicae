package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ipleiria.estg.dei.musicaev1.R;

public class RegisterFinalActivity extends AppCompatActivity {

    private Button buttonRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_final);

        buttonRegistar = findViewById(R.id.btnRegister);
        buttonRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterFinalActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
