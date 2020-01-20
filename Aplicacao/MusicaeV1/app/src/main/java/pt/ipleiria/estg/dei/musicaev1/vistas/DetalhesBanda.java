package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class DetalhesBanda extends AppCompatActivity {

    private Banda banda;
    public static  final String ID_BANDA = "idBanda";
    private EditText edNomeBanda, edCidadeBanda, edContactoBanda, edDescricaoBanda, edGeneroBanda, edLocalizacaoBanda, edCapaBanda;
    private int idBanda;
    ImageView imageView;
    private MenuItem menuItem;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idBanda = getIntent().getIntExtra(ID_BANDA, -1);


        edNomeBanda = findViewById(R.id.etBandName);
        edCidadeBanda = findViewById(R.id.etCity);
        edContactoBanda = findViewById(R.id.etPhone);
        edDescricaoBanda = findViewById(R.id.etDescription);
        //fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idBanda == -1){
                    setTitle("Adicionar Livro");
                    fab.setImageResource(R.drawable.ic_action_add);
                }else{
                    banda = Singleton.getInstance(getApplicationContext()).getBanda(idBanda);
                    fab.setImageResource(R.drawable.ic_action_save);
                    setTitle("Detalhes:" + banda.getNome());
                    mostrarBanda(banda);
                }
            }
        });
    }

    private Banda criarBanda(){
        String img = "http://amsi.dei.estg.ipleiria.pt/img/ipl_semfundo.png";
        Banda auxbanda = new Banda(0, edNomeBanda.getText().toString(), edGeneroBanda.getText().toString(), edLocalizacaoBanda.getText().toString(), Integer.parseInt(edContactoBanda.getText().toString()), edDescricaoBanda.getText().toString(), edCapaBanda.getText().toString());
        return auxbanda;
    }

    private Banda editarBanda(){
        banda.setNome(edNomeBanda.getText().toString());
        banda.setGenero(edGeneroBanda.getText().toString());
        banda.setLocalizacao(edLocalizacaoBanda.getText().toString());
        banda.setContacto(Integer.parseInt(edContactoBanda.getText().toString()));
        banda.setDescricao(edDescricaoBanda.getText().toString());
        banda.setCapa(edCapaBanda.getText().toString());
        return banda;
    }

    private void mostrarBanda(Banda banda){
        setTitle("Banda: " + banda.getNome());

        edNomeBanda.setText(banda.getNome());
        edGeneroBanda.setText(banda.getGenero());
        edLocalizacaoBanda.setText(banda.getLocalizacao());
        edContactoBanda.setText(""+banda.getContacto());
        edDescricaoBanda.setText(banda.getDescricao());
        edCapaBanda.setText(edCapaBanda.getText().toString());

        Glide.with(getApplicationContext())
                .load(banda.getCapa())
                .placeholder(R.drawable.ic_edit_profile)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    private void dialogRemover(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover Banda")


                .setMessage("Pretende mesmo remover o banda??'")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Singleton.getInstance(getApplicationContext()).removerBanda(idBanda);
                        finish();
                    }})

                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }})

                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idBanda != -1){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_detalhes_banda, menu);
            return super.onCreateOptionsMenu(menu);
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemRemover:
                dialogRemover();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
