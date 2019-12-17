package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvId, tvNome, tvEmail, tvDataNasc, tvNrTelemovel;
    private Perfil perfil;
    private SharedPreferences sharedPreferences;
    private Button buttonEditar, buttonBanda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = rootView.findViewById(R.id.tvUsername);
        tvId = rootView.findViewById(R.id.tvID);
        tvNome = rootView.findViewById(R.id.tvName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvDataNasc = rootView.findViewById(R.id.tvNumber);
        tvNrTelemovel = rootView.findViewById(R.id.tvBirthDate);


        perfil = Singleton.getInstance(getContext()).getPerfil(Integer.parseInt(getActivity().getIntent().getStringExtra(MenuMainActivity.CHAVE_ID)));
        String idText= "#" + perfil.getId();


        tvUsername.setText(perfil.getNome());
        tvId.setText(idText);

        buttonEditar = rootView.findViewById(R.id.btnEditProfile);
        buttonBanda = rootView.findViewById(R.id.btnBanda);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonBanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    private void mostrarPerfil(Perfil perfil){
        tvUsername.setText(perfil.getNome());
        tvId.setText(perfil.getId());
        tvNome.setText(perfil.getNome());
        tvDataNasc.setText(perfil.getDatanasc());
        tvNrTelemovel.setText(perfil.getNrtelemovel());
    }

    private Perfil editarPerfil(){
        perfil.setNome(perfil.getNome());
        perfil.setSexo(perfil.getSexo());
        perfil.setLocalidade(perfil.getLocalidade());
        perfil.setNrtelemovel(perfil.getNrtelemovel());
        perfil.setDatanasc(perfil.getDatanasc());

        return perfil;
    }

}