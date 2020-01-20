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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaFeedAdaptador;
import pt.ipleiria.estg.dei.musicaev1.listeners.FeedListener;
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;
import pt.ipleiria.estg.dei.musicaev1.utils.FeedJsonParser;


public class FeedFragment extends Fragment implements FeedListener {

    private ArrayList<Feed> listaFeed;
    private ListView lvListaFeed;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button buttonFiltro, buttonNome, buttonInstrumento;
    private Integer selecionado = 0;
    private ListaFeedAdaptador listaFeedAdaptador;

    public static final int RESULT_CODE_VER = 10;
    public static final int RESULT_CODE_EDITAR = 11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        lvListaFeed = rootView.findViewById(R.id.lvListaFeed);
        lvListaFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Feed tempFeed = (Feed) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), ProfileBandActivity.class);
                intent.putExtra(ProfileBandActivity.ID_BANDA, tempFeed.getId());
                intent.putExtra(ProfileBandActivity.NOME_BANDA, tempFeed.getNome());
                startActivityForResult(intent, RESULT_CODE_VER);
            }
        });

        Singleton.getInstance(getContext()).setFeedListener(this);
        Singleton.getInstance(getContext()).getAllBandasFeedAPI(getContext(), FeedJsonParser.isConnectionInternet(getContext()));

        swipeRefreshLayout = rootView.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Singleton.getInstance(getContext()).getAllBandasFeedAPI(getContext(), FeedJsonParser.isConnectionInternet(getContext()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        buttonFiltro = rootView.findViewById(R.id.btnFiltro);
        buttonFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
        });

        buttonNome = rootView.findViewById(R.id.btnNomeFeed);
        buttonInstrumento = rootView.findViewById(R.id.btnInstrumentoFeed);

        buttonNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------------------------------- XML -----------------------------------------
                switch (selecionado){
                    case 0:
                        selecionado = 1;
                        buttonNome.setBackgroundResource(R.drawable.button_branco_selecionado);
                        Toast.makeText(getContext(), "Nome", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        selecionado = 0;
                        buttonNome.setBackgroundResource(R.drawable.button_branco);
                        buttonInstrumento.setBackgroundResource(R.drawable.button_branco);
                        Toast.makeText(getContext(), "Limpo", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        selecionado = 1;
                        buttonNome.setBackgroundResource(R.drawable.button_branco_selecionado);
                        buttonInstrumento.setBackgroundResource(R.drawable.button_branco);
                        Toast.makeText(getContext(), "Nome", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return;
                }
                //--------------------------------------------------------------------------------------
            }


        });

        buttonInstrumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------------------------------- XML -----------------------------------------
                switch (selecionado){
                    case 0:
                        selecionado = 2;
                        buttonInstrumento.setBackgroundResource(R.drawable.button_branco_selecionado);
                        Toast.makeText(getContext(), "Instrumento", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        selecionado = 2;
                        buttonInstrumento.setBackgroundResource(R.drawable.button_branco_selecionado);
                        buttonNome.setBackgroundResource(R.drawable.button_branco);
                        Toast.makeText(getContext(), "Instrumento", Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        selecionado = 0;
                        buttonInstrumento.setBackgroundResource(R.drawable.button_branco);
                        buttonNome.setBackgroundResource(R.drawable.button_branco);
                        Toast.makeText(getContext(), "Limpo", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return;
                }
                //--------------------------------------------------------------------------------------
            }
        });

        return rootView;
    }

    @Override
    public void onRefreshListaBandasFeed(ArrayList<Feed> listaFeed) {
        System.out.println("--> onRefreshListaFeed" + listaFeed);
        if(!listaFeed.isEmpty()){
            listaFeedAdaptador = new ListaFeedAdaptador(getContext(), listaFeed);
            lvListaFeed.setAdapter(listaFeedAdaptador);
            listaFeedAdaptador.refresh(listaFeed);
        }
    }

    @Override
    public void onUpdateListaBandasFeed(Feed feed, int operacao) {

    }
}
