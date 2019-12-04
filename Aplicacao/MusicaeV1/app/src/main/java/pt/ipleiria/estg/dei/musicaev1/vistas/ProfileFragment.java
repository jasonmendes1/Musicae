package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvId, tvEmail;
    private Perfil perfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        perfil = Singleton.getInstance().getPerfil(1);

        tvUsername = rootView.findViewById(R.id.tvUsername);
        tvId = rootView.findViewById(R.id.tvID);
        tvEmail = rootView.findViewById(R.id.tvEmail);

        String idText= "#" + getActivity().getIntent().getStringExtra(MenuMainActivity.CHAVE_ID);

        tvUsername.setText(getActivity().getIntent().getStringExtra(MenuMainActivity.CHAVE_USERNAME));
        tvEmail.setText(getActivity().getIntent().getStringExtra(MenuMainActivity.CHAVE_EMAIL));
        tvId.setText(idText);

        return rootView;
    }
}