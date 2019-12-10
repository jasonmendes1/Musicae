package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.adaptadores.ListaFeedAdaptador;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;
import pt.ipleiria.estg.dei.musicaev1.modelos.auxFeed;


public class FeedFragment extends Fragment {

    private ArrayList<auxFeed> listaFeed;
    private ListView lvListaBandas;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        listaFeed = Singleton.getInstance(getContext()).getBandasFeed();
        lvListaBandas = rootView.findViewById(R.id.lvFeed);
        lvListaBandas.setAdapter(new ListaFeedAdaptador(getContext(), listaFeed));

        /*lvListaBandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                auxFeed tempLivro = (auxFeed) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetalhesLivro.class);
                intent.putExtra(DetalhesLivro.ID_LIVRO, tempLivro.getId());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetalhesLivro.class);
                startActivity(intent);
            }
        });*/

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);

        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<auxFeed> tempListaLivros = new ArrayList<>();
                for (auxFeed tempLivro: Singleton.getInstance(getContext()).getBandasFeed()) {
                    if(tempLivro.getNome().toLowerCase().contains(newText.toLowerCase())){
                        tempListaLivros.add(tempLivro);
                    }
                }

                lvListaBandas.setAdapter(new ListaFeedAdaptador(getContext(), tempListaLivros));
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        if(searchView != null){
            searchView.onActionViewCollapsed();
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemPesquisa){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
