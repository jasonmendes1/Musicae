package pt.ipleiria.estg.dei.musicaev1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.musicaev1.vistas.BandsFragment;
import pt.ipleiria.estg.dei.musicaev1.vistas.FeedFragment;
import pt.ipleiria.estg.dei.musicaev1.vistas.LoginActivity;
import pt.ipleiria.estg.dei.musicaev1.vistas.PortfolioFragment;
import pt.ipleiria.estg.dei.musicaev1.vistas.ProfileFragment;
import pt.ipleiria.estg.dei.musicaev1.vistas.WelcomeActivity;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;

    public static final String SECCAO_INFO_USER = "SECCAO_INFO_USER";
    public static final String CHAVE_USERNAME = "USERNAME";
    public static final String CHAVE_ID = "-1";
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
        String id = getIntent().getStringExtra(CHAVE_ID);

        String usernameId = username + "#" + id;
        View view = navigationView.getHeaderView(0);
        TextView textViewUser = view.findViewById(R.id.tvNome);
        textViewUser.setText(usernameId);
    }

    private void carregamentoFragmentoInicial(){
        navigationView.setCheckedItem(R.id.nav_profile);
        Fragment fragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        setTitle("Profile");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_bandas:
                fragment = new BandsFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_portfolio:
                fragment = new PortfolioFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_feed:
                fragment = new FeedFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_logout:
                sharedPreferences = getSharedPreferences(MenuMainActivity.SECCAO_INFO_USER, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(MenuMainActivity.SECCAO_INFO_USER, "-1");
                editor.apply();
                dialogLogout();
                break;
            default:
                fragment = new ProfileFragment();
        }

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.contentFragment,
                    fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialogLogout(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout")
                .setMessage("Do you really want to Logout?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuMainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }})

                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }})
                .setIcon(android.R.drawable.ic_lock_power_off)
                .show();
    }
}
