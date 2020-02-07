package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class CreateBandActivity extends AppCompatActivity {

    private Banda banda;
    private EditText edNomeBanda, edCidadeBanda, edContactoBanda, edDescricaoBanda;
    private int idBanda;
    private Spinner spinner;
    private Button buttonCancelar, buttonCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);


        edNomeBanda = findViewById(R.id.etBandName);
        edCidadeBanda = findViewById(R.id.etCity);
        edContactoBanda = findViewById(R.id.etPhone);
        edDescricaoBanda = findViewById(R.id.etDescription);

        //----------------------------------------------------------------------- Select Gender ------------------------------------------------------------------------------
        spinner = findViewById(R.id.spinnerGenre);


        List<String> categorias = Singleton.getInstance(getApplicationContext()).generosAPI;

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //------------------------------------------------------------ Buttons ------------------------------------------------------------

        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonCriar = findViewById(R.id.btnCriarBanda);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banda banda = new Banda(0, edNomeBanda.getText().toString(), edDescricaoBanda.getText().toString(), edCidadeBanda.getText().toString(), Integer.parseInt(edContactoBanda.getText().toString()), "logo.png", "0", spinner.getSelectedItemPosition()+ "");
                Singleton.getInstance(getApplicationContext()).adicionarBandaAPI(banda, getApplicationContext());
                Singleton.getInstance(getApplicationContext()).adicionarMembroBandaAPI(banda.getNome());
                finish();
            }
        });
    }
}
