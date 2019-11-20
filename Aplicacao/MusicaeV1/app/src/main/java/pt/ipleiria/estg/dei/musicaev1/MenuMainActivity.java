package pt.ipleiria.estg.dei.musicaev1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.musicaev1.vistas.ProfileFragment;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;

    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    public static final String CHAVE_USERNAME = "USERNAME";
    public static final String CHAVE_EMAIL = "EMAIL";
    private String username = "";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        toggle.syncState();
        drawer.addDrawerListener(toggle);

        carregarCabecalho();

        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(this);

        carregamentoFragmentoInicial();
    }

    private void carregarCabecalho() {
        username = getIntent().getStringExtra(CHAVE_USERNAME);

        View view = navigationView.getHeaderView(0);
        TextView textViewUser = view.findViewById(R.id.tvNome);
        textViewUser.setText(username);
    }

    private void carregamentoFragmentoInicial(){
        navigationView.setCheckedItem(R.id.nav_profile);
        Fragment fragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        setTitle("Profile");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
}
