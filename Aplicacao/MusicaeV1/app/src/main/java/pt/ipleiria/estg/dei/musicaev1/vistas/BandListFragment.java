package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.musicaev1.R;

public class BandListFragment extends Fragment {

    private FloatingActionButton fab;
    Button buttonAtual, buttonPassado, buttonPendente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_band_list, container, false);

        fab = rootView.findViewById(R.id.fabADD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateBandActivity.class);
                startActivity(intent);
            }
        });

        //---------------------------------------------------- Buttons --------------------------------------------------
        buttonAtual = rootView.findViewById(R.id.btnAtuais);
        buttonPassado = rootView.findViewById(R.id.btnPassadas);
        buttonPendente = rootView.findViewById(R.id.btnPendentes);


        //------------------------------------------------------------------------------------------------------------

        return rootView;
    }

}
