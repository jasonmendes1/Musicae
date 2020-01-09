package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class FilterActivity extends AppCompatActivity {

    private Button buttonProcurar;
    private CheckBox checkBoxPeloMenos, checkBoxExato;
    private Spinner spinnerExperience, spinnerCommitment;
    private EditText editTextSkill, editTextGenre, editTextN1, editTextN2;
    private String[] listItemsSkill, listItemsGenre;
    boolean[] checkedItemsSkill, checkedItemsGenre;
    //Items que o user selecionou
    private ArrayList<Integer> userItemsSkill = new ArrayList<>();
    private ArrayList<Integer> userItemsGenre = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        buttonProcurar = findViewById(R.id.btnProcurarFiltro);
        buttonProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //----------------------------------------------------------------------- Select Skill ------------------------------------------------------------------------------
        editTextSkill = findViewById(R.id.etSkill);
        editTextSkill.setFocusable(false);

        listItemsSkill = Singleton.getInstance(getApplicationContext()).getHabilidadesFiltro();
        System.out.println("-->" + listItemsSkill);
        checkedItemsSkill = new boolean[listItemsSkill.length];;

        editTextSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Habilidades");
                mBuilder.setMultiChoiceItems(listItemsSkill, checkedItemsSkill, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            userItemsSkill.add(position);
                        }else{
                            userItemsSkill.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < userItemsSkill.size(); i++) {
                            item = item + listItemsSkill[userItemsSkill.get(i)];
                            if (i != userItemsSkill.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        editTextSkill.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Limpar Tudo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItemsSkill.length; i++) {
                            checkedItemsSkill[i] = false;
                            userItemsSkill.clear();
                            editTextSkill.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        //----------------------------------------------------------------------- Select Age ------------------------------------------------------------------------------
        editTextN1 = findViewById(R.id.etFrom);
        editTextN2 = findViewById(R.id.etTo);

        editTextN2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                try{
                    Double  n1 = Double.parseDouble (editTextN1.getText().toString());
                    Double  n2 = Double.parseDouble (editTextN2.getText().toString());

                    if(n2 < n1){
                        editTextN2.setError("Erro Idade");
                    }

                }catch (Exception e){

                }
            }
        });

        //----------------------------------------------------------------------- Select Genre ------------------------------------------------------------------------------
        editTextGenre = findViewById(R.id.etGenero);
        editTextGenre.setFocusable(false);

        listItemsGenre = Singleton.getInstance(getApplicationContext()).getGeneroFiltro();
        checkedItemsGenre = new boolean[listItemsGenre.length];

        editTextGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Indústrias");
                mBuilder.setMultiChoiceItems(listItemsGenre, checkedItemsGenre, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            userItemsGenre.add(position);
                        }else{
                            userItemsGenre.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < userItemsGenre.size(); i++) {
                            item = item + listItemsGenre[userItemsGenre.get(i)];
                            if (i != userItemsGenre.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        editTextGenre.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Limpar Tudo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItemsGenre.length; i++) {
                            checkedItemsGenre[i] = false;
                            userItemsGenre.clear();
                            editTextGenre.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        //----------------------------------------------------------------------- Select Experience ------------------------------------------------------------------------------
        spinnerExperience = findViewById(R.id.spinnerExperiencia);

        List<String> categoriasExperiencia = new ArrayList<>();
        categoriasExperiencia.add(0, "");
        categoriasExperiencia.add(1, "Iniciante");
        categoriasExperiencia.add(2, "Intermédio");
        categoriasExperiencia.add(3, "Moderado");
        categoriasExperiencia.add(4, "Avançado");
        categoriasExperiencia.add(5, "Experiente");

        ArrayAdapter<String> dataAdapterExperiencia;
        dataAdapterExperiencia = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoriasExperiencia);
        dataAdapterExperiencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExperience.setAdapter(dataAdapterExperiencia);

        spinnerExperience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        //----------------------------------------------------------------------- Select Commitment ------------------------------------------------------------------------------
        checkBoxPeloMenos = findViewById(R.id.chkPeloMenos);
        checkBoxExato = findViewById(R.id.chkExato);

        checkBoxPeloMenos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    checkBoxExato.setChecked(false);
                }
            }
        });

        checkBoxExato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    checkBoxPeloMenos.setChecked(false);
                }
            }
        });


        spinnerCommitment = findViewById(R.id.spinnerCompromisso);

        List<String> categoriasCompromisso = new ArrayList<>();
        categoriasCompromisso.add(0, "");
        categoriasCompromisso.add(1, "Diversão");
        categoriasCompromisso.add(2, "Moderadamente Comprometido");
        categoriasCompromisso.add(3, "Comprometido");
        categoriasCompromisso.add(4, "Muito Comprometido");
        categoriasCompromisso.add(5, "Tour");

        ArrayAdapter<String> dataAdapterCompromisso;
        dataAdapterCompromisso = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoriasCompromisso);
        dataAdapterCompromisso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommitment.setAdapter(dataAdapterCompromisso);

        spinnerCommitment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    }
}
