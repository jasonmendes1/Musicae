package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ipleiria.estg.dei.musicaev1.R;

public class SearchBandActivity extends AppCompatActivity {

    private Button buttonCancelar, buttonCriarProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_band);

        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonCriarProcura = findViewById(R.id.btnSearch);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCriarProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
