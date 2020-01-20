package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class SearchBandActivity extends AppCompatActivity {

    public static  final String ID_BANDA = "idBanda";
    private int idBanda;
    private Spinner spinnerInstrumento, spinnerExperiencia, spinnerCompromisso;
    private Button buttonCancelar, buttonCriarProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_band);

        idBanda = getIntent().getIntExtra(ID_BANDA, 0);

        buttonCancelar = findViewById(R.id.btnCancelar);
        buttonCriarProcura = findViewById(R.id.btnCriarProcura);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCriarProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance(getApplicationContext()).adicionarBandaFeedAPI(criarFeed(),getApplicationContext());
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private Feed criarFeed(){
        String img = "http://amsi.dei.estg.ipleiria.pt/img/ipl_semfundo.png";
        Feed auxFeed = new Feed(0, edTitulo.getText().toString(), edSerie.getText().toString(), edAutor.getText().toString(), Integer.parseInt(edAno.getText().toString()), img);
        return auxFeed;
    }
}
