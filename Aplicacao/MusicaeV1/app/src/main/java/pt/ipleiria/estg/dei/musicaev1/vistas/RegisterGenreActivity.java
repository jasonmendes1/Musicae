package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaGeneroAdaptador;
import pt.ipleiria.estg.dei.musicaev1.modelos.Genero;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterGenreActivity extends AppCompatActivity {

    private ArrayList<Genero> listaGeneros;
    private ListView lvListaGeneros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_genre);

        listaGeneros = Singleton.getInstance().getGeneros();
        lvListaGeneros = findViewById(R.id.lvListaGeneros);
        lvListaGeneros.setAdapter(new ListaGeneroAdaptador(this, listaGeneros));

        /*lvListaGeneros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegisterGenreActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
