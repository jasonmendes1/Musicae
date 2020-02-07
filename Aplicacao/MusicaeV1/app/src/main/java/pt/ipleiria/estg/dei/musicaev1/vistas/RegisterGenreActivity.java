package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class RegisterGenreActivity extends AppCompatActivity {
    public static  final String ID_HABILIDADE = "idHabilidade";

    private ListView lvListaGeneros;
    private List<String> listaGeneros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_genre);

        lvListaGeneros = findViewById(R.id.lvListaGeneros);
        listaGeneros = Singleton.getInstance(getApplicationContext()).generosAPI;

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaGeneros);
        // Set The Adapter
        lvListaGeneros.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        lvListaGeneros.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Intent intent = new Intent(RegisterGenreActivity.this, RegisterDetailsActivity.class);
                intent.putExtra(RegisterDetailsActivity.ID_HABILIDADE, getIntent().getIntExtra(ID_HABILIDADE, 0));
                intent.putExtra(RegisterDetailsActivity.ID_GENERO, position);
                startActivity(intent);
            }
        });
    }
}
