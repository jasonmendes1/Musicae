package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.musicaev1.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this, "Ola", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Pedro", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Alves", Toast.LENGTH_SHORT).show();
    }
}