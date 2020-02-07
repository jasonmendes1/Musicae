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

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaBandaAdaptador;
import pt.ipleiria.estg.dei.musicaev1.listeners.BandaHabilidadeListener;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaMembro;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;
import pt.ipleiria.estg.dei.musicaev1.utils.BandaHabilidadeJsonParser;

public class BandListFragment extends Fragment implements BandaHabilidadeListener {

    private Button buttonAtual, buttonPassado, buttonPendente;
    private ListView lvListaBandas;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListaBandaAdaptador listaBandaAdaptador;
    private boolean isHistorico = false;


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
                if(!isHistorico){
                    BandaMembro temp = (BandaMembro)  parent.getItemAtPosition(position);
                    Intent intent = new Intent(getContext(), ProfileBandActivity.class);
                    intent.putExtra(ProfileBandActivity.ID_BANDA, temp.getIdBanda());
                    intent.putExtra(ProfileBandActivity.NOME_BANDA, temp.getBandaNome());
                    intent.putExtra(ProfileBandActivity.FEED, -1);
                    startActivityForResult(intent, RESULT_CODE_VER);
                }
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
                if(isHistorico){
                    Singleton.getInstance(getContext()).getBandasHistoricoAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));
                }else{
                    Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });



        //---------------------------------------------------- Buttons --------------------------------------------------
        buttonAtual = rootView.findViewById(R.id.btnAtuais);
        buttonPassado = rootView.findViewById(R.id.btnPassadas);
        buttonPendente = rootView.findViewById(R.id.btnPendentes);


        //------------------------------------------------------------------------------------------------------------

        Singleton.getInstance(getContext()).setBandaHabilidadeListener(this);
        Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));

        buttonPassado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHistorico = true;
                lvListaBandas.setAdapter(null);
                Singleton.getInstance(getContext()).getBandasHistoricoAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));
            }
        });

        buttonAtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHistorico = false;
                lvListaBandas.setAdapter(null);
                Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        System.out.println("--> Onresume triggered");
        Singleton.getInstance(getContext()).getBandasPerfilAPI(getContext(), BandaHabilidadeJsonParser.isConnectionInternet(getContext()));
        super.onResume();
    }

    @Override
    public void onRefreshBandaHabilidades(ArrayList<BandaMembro> MinhasBandas) {
        if(!MinhasBandas.isEmpty()){
            listaBandaAdaptador = new ListaBandaAdaptador(getContext(), MinhasBandas);
            lvListaBandas.setAdapter(listaBandaAdaptador);
            listaBandaAdaptador.refresh(MinhasBandas);
        }
    }
}
