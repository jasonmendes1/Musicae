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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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
    private Spinner spinnerGenero;
    private int idBanda;
    ImageView imageView;
    private MenuItem menuItem;
    private Button buttonCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);

        idBanda = getIntent().getIntExtra(ID_BANDA, -1);

        edNomeBanda = findViewById(R.id.etBandName);
        spinnerGenero = findViewById(R.id.spinnerGenre);
        edLocalizacaoBanda = findViewById(R.id.etCity);
        edContactoBanda = findViewById(R.id.etPhone);
        edDescricaoBanda = findViewById(R.id.etDescription);
        //fab = findViewById(R.id.fab);

        buttonCriar = findViewById(R.id.btnCRIARBANDA);
        buttonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance(getApplicationContext()).adicionarBandaAPI(criarBanda(),getApplicationContext());
                finish();
                System.out.println("-->" + criarBanda().toString());
            }
        });
    }

    private Banda criarBanda(){
        String img = "http://amsi.dei.estg.ipleiria.pt/img/ipl_semfundo.png";
        Banda auxbanda = new Banda(0, edNomeBanda.getText().toString(), spinnerGenero.getSelectedItem().toString(), edLocalizacaoBanda.getText().toString(), Integer.parseInt(edContactoBanda.getText().toString()), edDescricaoBanda.getText().toString(), img);
        return auxbanda;
    }

    private Banda editarBanda(){
        banda.setNome(edNomeBanda.getText().toString());
        banda.setGenero(spinnerGenero.getSelectedItem().toString());
        banda.setLocalizacao(edLocalizacaoBanda.getText().toString());
        banda.setContacto(Integer.parseInt(edContactoBanda.getText().toString()));
        banda.setDescricao(edDescricaoBanda.getText().toString());
        banda.setCapa(edCapaBanda.getText().toString());
        return banda;
    }

    private void mostrarBanda(Banda banda){
        setTitle("Banda: " + banda.getNome());

        edNomeBanda.setText(banda.getNome());
        spinnerGenero.setPrompt(banda.getGenero());
        edLocalizacaoBanda.setText(banda.getLocalizacao());
        edContactoBanda.setText(""+banda.getContacto());
        edDescricaoBanda.setText(banda.getDescricao());
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
