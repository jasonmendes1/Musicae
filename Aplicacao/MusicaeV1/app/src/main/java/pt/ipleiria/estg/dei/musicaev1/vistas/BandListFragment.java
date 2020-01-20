package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaBandaAdaptador;
import pt.ipleiria.estg.dei.musicaev1.listeners.BandaHabilidadeListener;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;
import pt.ipleiria.estg.dei.musicaev1.utils.FeedJsonParser;

public class BandListFragment extends Fragment implements BandaHabilidadeListener {

    private Button buttonAtual, buttonPassado, buttonPendente;
    private ArrayList<Banda> listaBandas;
    private ListView lvListaBandas;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListaBandaAdaptador listaBandaAdaptador;


    public static final int RESULT_CODE_CRIAR = 12;
    public static final int RESULT_CODE_VER = 10;
    public static final int RESULT_CODE_GUARDAR_REMOVER = 11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        final View rootView = inflater.inflate(R.layout.fragment_band_list, container, false);

        lvListaBandas = rootView.findViewById(R.id.lvListaBandas);
        lvListaBandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Banda tempBanda = (Banda) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), ProfileBandActivity.class);
                intent.putExtra(ProfileBandActivity.NOME_BANDA, tempBanda.getNome());
                startActivityForResult(intent, RESULT_CODE_VER);*/
            }
        });

        FloatingActionButton fab = rootView.findViewById(R.id.fabADDbanda);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateBandActivity.class);
                startActivityForResult(intent, RESULT_CODE_CRIAR);
            }
        });

        swipeRefreshLayout = rootView.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), FeedJsonParser.isConnectionInternet(getContext()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        //---------------------------------------------------- Buttons --------------------------------------------------
        buttonAtual = rootView.findViewById(R.id.btnAtuais);
        buttonPassado = rootView.findViewById(R.id.btnPassadas);
        buttonPendente = rootView.findViewById(R.id.btnPendentes);


        //------------------------------------------------------------------------------------------------------------

        Singleton.getInstance(getContext()).setBandaHabilidadeListener(this);
        Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), FeedJsonParser.isConnectionInternet(getContext()));



        return rootView;
    }

    @Override
    public void onResume() {
        Singleton.getInstance(getContext()).getAllBandasFeedAPI(getContext(), FeedJsonParser.isConnectionInternet(getContext()));
        if(searchView != null){
            searchView.onActionViewCollapsed();
        }
        super.onResume();
    }

    @Override
    public void onRefreshBandaHabilidades(ArrayList<BandaHabilidade> MinhasBandas) {
        if(!MinhasBandas.isEmpty()){
            listaBandaAdaptador = new ListaBandaAdaptador(getContext(), MinhasBandas);
            lvListaBandas.setAdapter(listaBandaAdaptador);
            listaBandaAdaptador.refresh(MinhasBandas);
        }
    }
}
