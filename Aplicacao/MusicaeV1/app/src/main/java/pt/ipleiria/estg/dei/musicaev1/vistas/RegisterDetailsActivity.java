package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;

public class RegisterDetailsActivity extends AppCompatActivity {
    public static  final String ID_HABILIDADE = "idHabilidade";
    public static  final String ID_GENERO = "idGenero";





    private Spinner spinner;

    private TextView mDisplayDate;
    private DatePickerDialog .OnDateSetListener mDateSetListener;
    private EditText etNome, etMail, etLocalidade;
    private Button buttonNext;
    private String item, date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        //----------------------------------------------------------------------- Select Gender ------------------------------------------------------------------------------
        spinner = findViewById(R.id.spinnerGender);

        etNome = findViewById(R.id.etFirstName);
        etMail = findViewById(R.id.etEmail);
        etLocalidade = findViewById(R.id.etLocalidade);


        List<String> categorias = new ArrayList<>();
        categorias.add(0, "Sexo");
        categorias.add(1, "Homem");
        categorias.add(2, "Mulher");
        categorias.add(3, "?? Outro ??");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) == "Sexo"){

                }else{
                    item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selecionado: " + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //--------------------------------------------------------------------- Select Birth Date ------------------------------------------------------------------------------
        mDisplayDate = findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterDetailsActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                date = year + "/" + month + "/" + day + " 00:00:00";
                mDisplayDate.setText(date);
            }
        };

        //-----------------------

        buttonNext = findViewById(R.id.button);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailsActivity.this, RegisterFinalActivity.class);

                System.out.println("--> DETAILS: profileNome="+ etNome.getText().toString() + " profileSexo=" + item
                        + " userEmail=" + etMail.getText().toString() + " profileLocalidade=" + etLocalidade.getText().toString()
                        + " profileDataNasc=" + date
                );

                intent.putExtra(RegisterFinalActivity.ID_HABILIDADE, getIntent().getIntExtra(ID_HABILIDADE, 0));
                intent.putExtra(RegisterFinalActivity.ID_GENERO, getIntent().getIntExtra(ID_GENERO, 0));

                intent.putExtra(RegisterFinalActivity.PROFILE_NOME, etNome.getText().toString());
                intent.putExtra(RegisterFinalActivity.PROFILE_SEXO, item);
                intent.putExtra(RegisterFinalActivity.USER_EMAIL, etMail.getText().toString());
                intent.putExtra(RegisterFinalActivity.PROFILE_LOCALIDADE, etLocalidade.getText().toString());
                intent.putExtra(RegisterFinalActivity.PROFILE_DATANASC, date);
                startActivity(intent);
            }
        });
    }
}
