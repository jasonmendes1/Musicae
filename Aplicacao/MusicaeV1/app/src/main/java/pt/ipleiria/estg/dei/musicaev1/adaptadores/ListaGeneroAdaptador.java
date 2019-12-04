package pt.ipleiria.estg.dei.musicaev1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Genero;
import pt.ipleiria.estg.dei.musicaev1.modelos.Habilidade;

public class ListaGeneroAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Genero> generos;


    public ListaGeneroAdaptador(Context context, ArrayList<Genero> generos) {
        this.context = context;
        this.generos = generos;
    }

    @Override
    public int getCount() { return generos.size(); }

    @Override
    public Object getItem(int position) {
        return generos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return generos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_genero, null);
        }

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(generos.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView nome;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNomeGenero);
        }

        public void update(Genero genero){
            nome.setText(genero.getNome());
        }
    }
}
