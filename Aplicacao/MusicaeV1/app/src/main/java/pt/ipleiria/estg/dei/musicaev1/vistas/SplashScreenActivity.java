package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.musicaev1.R;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SLAPSH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(mainIntent);
            }
        },SLAPSH_TIME_OUT);
    }
}
