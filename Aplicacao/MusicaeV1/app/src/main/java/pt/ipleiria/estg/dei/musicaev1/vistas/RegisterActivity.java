package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.musicaev1.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonMusico;
    private Button buttonIndustria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonMusico = findViewById(R.id.btnMusico);
        buttonIndustria = findViewById(R.id.btnIndustria);

        buttonMusico.setOnClickListener(this);
        buttonIndustria.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnMusico:
                Intent intent = new Intent(RegisterActivity.this, RegisterSkillActivity.class);
                startActivity(intent);
                break;
            case R.id.btnIndustria:
                Intent intent2 = new Intent(RegisterActivity.this, RegisterIndustryActivity.class);
                startActivity(intent2);
                break;
        }
    }
}