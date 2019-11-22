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
import pt.ipleiria.estg.dei.musicaev1.modelos.Habilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterSkillActivity extends AppCompatActivity {

    private ArrayList<Habilidade> listaHabilidades;
    private ListView lvListaHabilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_skill);

        listaHabilidades = Singleton.getInstance().getHabilidades();
        lvListaHabilidades = findViewById(R.id.lvListaHabilidades);
        lvListaHabilidades.setAdapter(new ListaHabilidadeAdaptador(this, listaHabilidades));

        lvListaHabilidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegisterSkillActivity.this, RegisterGenreActivity.class);
                startActivity(intent);
            }
        });
    }
}
