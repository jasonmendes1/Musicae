package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvId, tvEmail;
    private Perfil perfil;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = rootView.findViewById(R.id.tvUsername);
        tvId = rootView.findViewById(R.id.tvID);
        tvEmail = rootView.findViewById(R.id.tvEmail);


        perfil = Singleton.getInstance(getContext()).getPerfil(Integer.parseInt(getActivity().getIntent().getStringExtra(MenuMainActivity.CHAVE_ID)));
        String idText= "#" + perfil.getId();


        tvUsername.setText(perfil.getNome());
        tvId.setText(idText);

        return rootView;
    }
}