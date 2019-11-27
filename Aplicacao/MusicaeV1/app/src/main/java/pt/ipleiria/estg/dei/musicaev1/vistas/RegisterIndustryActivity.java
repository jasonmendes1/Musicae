package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaHabilidadeAdaptador;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaIndustriaAdaptador;
import pt.ipleiria.estg.dei.musicaev1.modelos.Habilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.Industria;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterIndustryActivity extends AppCompatActivity{

    private ArrayList<Industria> listaIndustrias;
    private ListView lvListaIndustrias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_industry);

        listaIndustrias = Singleton.getInstance().getIndustrias();
        lvListaIndustrias = findViewById(R.id.lvListaIndustrias);
        lvListaIndustrias.setAdapter(new ListaIndustriaAdaptador(this, listaIndustrias));

        lvListaIndustrias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegisterIndustryActivity.this, RegisterIndustryActivity.class);
                startActivity(intent);
            }
        });
    }
}
