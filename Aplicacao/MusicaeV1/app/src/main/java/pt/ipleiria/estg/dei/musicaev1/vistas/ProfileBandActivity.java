package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.musicaev1.R;

public class ProfileBandActivity extends AppCompatActivity {

    private Button buttonEditar, buttonCandidatos;
    private FloatingActionButton fabProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_band);

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
                startActivity(intent);
            }
        });
    }
}
