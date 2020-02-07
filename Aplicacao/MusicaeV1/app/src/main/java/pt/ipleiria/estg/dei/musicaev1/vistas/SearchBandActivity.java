package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class SearchBandActivity extends AppCompatActivity {

    public static  final String NOME_BANDA = "nomeBanda";
    public static  final String ID_BANDA = "idBanda";
    private Feed feed;
    private String nomeBanda;
    private int idBanda;
    private Spinner spinnerInstrumento, spinnerExperiencia, spinnerCompromisso;
    private Button buttonCancelar, buttonCriarProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_band);

        nomeBanda = getIntent().getStringExtra(NOME_BANDA);
        idBanda = getIntent().getIntExtra(ID_BANDA, 0);

        System.out.println("--> idBanda: " + idBanda);
        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonCriarProcura = findViewById(R.id.btnCriarProcura);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //----------------------------------------------------------------------- Select Instrumento ------------------------------------------------------------------------------
        spinnerInstrumento = findViewById(R.id.spinnerInstrumento);

        List<String> instrumentos = Singleton.getInstance(getApplicationContext()).habilidadesAPI;

        ArrayAdapter<String> dataAdapterInstrumento;
        dataAdapterInstrumento = new ArrayAdapter(this, android.R.layout.simple_spinner_item, instrumentos);
        dataAdapterInstrumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstrumento.setAdapter(dataAdapterInstrumento);


        spinnerInstrumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) == ""){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------------- Select Compromisso ------------------------------------------------------------------------------
        spinnerCompromisso = findViewById(R.id.spinnerCompromisso);

        List<String> compromissos = new ArrayList<>();
        compromissos.add(0, "");
        compromissos.add(1, "Diversao");
        compromissos.add(2, "Moderadamente Comprometido");
        compromissos.add(3, "Comprometido");
        compromissos.add(4, "Muito Comprometido");
        compromissos.add(5, "Tour");

        ArrayAdapter<String> dataAdapterCompromisso;
        dataAdapterCompromisso = new ArrayAdapter(this, android.R.layout.simple_spinner_item, compromissos);
        dataAdapterCompromisso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompromisso.setAdapter(dataAdapterCompromisso);

        spinnerCompromisso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) == ""){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------------------------------------- Select Experiencia ------------------------------------------------------------------------------
        spinnerExperiencia = findViewById(R.id.spinnerExperiencia);

        List<String> experiencias = new ArrayList<>();
        experiencias.add(0, "");
        experiencias.add(1, "Aprendiz");
        experiencias.add(2, "Novato");
        experiencias.add(3, "Experiente");
        experiencias.add(4, "Profissional");

        ArrayAdapter<String> dataAdapterExperiencia;
        dataAdapterExperiencia = new ArrayAdapter(this, android.R.layout.simple_spinner_item, experiencias);
        dataAdapterExperiencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExperiencia.setAdapter(dataAdapterExperiencia);

        spinnerExperiencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) == ""){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonCriarProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance(getApplicationContext()).adicionarBandaFeedAPI(idBanda, spinnerInstrumento.getSelectedItemId() + "", spinnerExperiencia.getSelectedItem().toString()+ "", spinnerCompromisso.getSelectedItem().toString() + "",getApplicationContext());
                finish();
            }
        });
    }
}
