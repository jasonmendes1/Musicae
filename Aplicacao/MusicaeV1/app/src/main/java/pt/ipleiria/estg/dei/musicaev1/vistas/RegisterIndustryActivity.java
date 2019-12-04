package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaIndustriaAdaptador;
import pt.ipleiria.estg.dei.musicaev1.modelos.Industria;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterIndustryActivity extends AppCompatActivity{

    private ArrayList<Industria> listaIndustria;
    private ListView lvListaIndustria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_industry);

        listaIndustria = Singleton.getInstance().getIndustrias();
        lvListaIndustria = findViewById(R.id.lvListaIndustrias);
        lvListaIndustria.setAdapter(new ListaIndustriaAdaptador(this, listaIndustria));


        lvListaIndustria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegisterIndustryActivity.this, RegisterGenreActivity.class);
                startActivity(intent);
            }
        });
    }
}
