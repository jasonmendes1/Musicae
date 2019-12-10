package pt.ipleiria.estg.dei.musicaev1.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SLAPSH_TIME_OUT = 1000;

    private SharedPreferences sharedPreferences;
    private Perfil perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        if(checkSharedPreferences()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MenuMainActivity.class);
                    perfil = Singleton.getInstance(getApplicationContext()).getPerfil(Integer.parseInt(sharedPreferences.getString(MenuMainActivity.SECCAO_INFO_USER, "-1")));
                    mainIntent.putExtra(MenuMainActivity.CHAVE_USERNAME, perfil.getUsername());
                    mainIntent.putExtra(MenuMainActivity.CHAVE_ID, ""+ perfil.getIdperfil());
                    startActivity(mainIntent);
                }
            },SLAPSH_TIME_OUT);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                    startActivity(mainIntent);
                }
            },SLAPSH_TIME_OUT);
        }
    }



    public boolean checkSharedPreferences(){
        sharedPreferences = getSharedPreferences(MenuMainActivity.SECCAO_INFO_USER, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(MenuMainActivity.SECCAO_INFO_USER, "-1").equals("-1")){
            return false;
        }
        return true;
    }
}
