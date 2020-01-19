package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileBandActivity extends AppCompatActivity {

    public static  final String ID_BANDA = "idBanda";
    private Banda banda;
    private TextView tvName, tvDescription, tvGenre, tvNumber, tvCity;
    private ImageView ivBannner;
    private int idBanda;
    private Button buttonEditar, buttonCandidatos;
    private FloatingActionButton fabProcura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_band);

        idBanda = getIntent().getIntExtra(ID_BANDA, 0);


        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvGenre = findViewById(R.id.tvGenre);
        tvNumber = findViewById(R.id.tvNumber);
        tvCity = findViewById(R.id.tvCity);
        ivBannner = findViewById(R.id.ivBannner);


            banda = Singleton.getInstance(getApplicationContext()).getBanda(idBanda);
            mostrarBanda(banda);

        buttonEditar = findViewById(R.id.btnEditarBanda);
        buttonCandidatos = findViewById(R.id.btnCandidatos);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonCandidatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBandActivity.this, CandidaturesActivity.class);
                startActivity(intent);
            }
        });

        fabProcura = findViewById(R.id.fabProcura);
        fabProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileBandActivity.this, SearchBandActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mostrarBanda(Banda banda){
        tvName.setText(banda.getNome());
        tvDescription.setText(banda.getDescricao());
        tvGenre.setText(banda.getGenero());
        tvNumber.setText(banda.getContacto());
        tvCity.setText(banda.getLocalizacao());
        //imgView.setImageResource(livro.getCapa());
        Glide.with(getApplicationContext())
                .load(banda.getCapa())
                .placeholder(R.drawable.banner)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivBannner);
    }
}
