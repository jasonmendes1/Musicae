package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;

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

        //----------------------------------------------------------------------- Select Gender ------------------------------------------------------------------------------
        spinner = findViewById(R.id.spinnerGenre);

        List<String> categorias = new ArrayList<>();
        categorias.add(0, "Genero");
        categorias.add(1, "Rap");
        categorias.add(2, "Jazz");
        categorias.add(3, "Rock");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) == "Sexo"){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selecionado: " + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

            }
        });
    }
}
