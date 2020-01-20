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
    private Feed feed;
    private String nome;
    private Spinner spinnerInstrumento, spinnerExperiencia, spinnerCompromisso;
    private Button buttonCancelar, buttonCriarProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_band);

        final String nomeBanda = getIntent().getStringExtra(NOME_BANDA);
        nome = nomeBanda;

        buttonCancelar = findViewById(R.id.btnCancelar);
        //buttonCriarProcura = findViewById(R.id.btnCriarProcura);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //----------------------------------------------------------------------- Select Instrumento ------------------------------------------------------------------------------
        //spinnerInstrumento = findViewById(R.id.spinnerInstrumento);

        List<String> instrumentos = new ArrayList<>();
        instrumentos.add(0, "");
        instrumentos.add(1, "Harpa");
        instrumentos.add(2, "Guitarra");
        instrumentos.add(3, "Piano");

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
        compromissos.add(1, "Pouco");
        compromissos.add(2, "Médio");
        compromissos.add(3, "Muito");

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
        //------------------------------------------------------------------------------------------------------------------------------------------------------
        buttonCriarProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance(getApplicationContext()).adicionarBandaFeedAPI(criarFeed(),getApplicationContext());
                setResult(RESULT_OK);
                System.out.println("-->Nome: " + nomeBanda);
                finish();
            }
        });
    }

    private Feed criarFeed(){
        String img = "http://amsi.dei.estg.ipleiria.pt/img/ipl_semfundo.png";
        Feed auxFeed = new Feed(0, nome, spinnerInstrumento.getSelectedItem().toString(), spinnerCompromisso.getSelectedItem().toString(), spinnerExperiencia.getSelectedItem().toString(), img);
        return auxFeed;
    }

    private Feed editarFeed(){
        feed.setNome(nome);
        feed.setInstrumento(spinnerInstrumento.getSelectedItem().toString());
        feed.setCompromisso(spinnerCompromisso.getSelectedItem().toString());
        feed.setExperiencia(spinnerExperiencia.getSelectedItem().toString());

        return feed;
    }
}
