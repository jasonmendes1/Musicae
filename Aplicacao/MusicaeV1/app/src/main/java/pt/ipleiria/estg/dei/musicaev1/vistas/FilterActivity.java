package pt.ipleiria.estg.dei.musicaev1.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pt.ipleiria.estg.dei.musicaev1.R;

public class FilterActivity extends AppCompatActivity {

    private Button buttonProcurar;
    private EditText editTextSkill;
    private String[] listItems;
    boolean[] checkedItems;
    //Items que o user selecionou
    private ArrayList<Integer> userItems = new ArrayList<>();

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

        listItems = getResources().getStringArray(R.array.skillsArray);
        checkedItems = new boolean[listItems.length];;

        editTextSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Titulo");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            userItems.add(position);
                        }else{
                            userItems.remove((Integer.valueOf(position)));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < userItems.size(); i++) {
                            item = item + listItems[userItems.get(i)];
                            if (i != userItems.size() - 1) {
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
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                            userItems.clear();
                            editTextSkill.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        //----------------------------------------------------------------------- Select Industry ------------------------------------------------------------------------------
    }
}
