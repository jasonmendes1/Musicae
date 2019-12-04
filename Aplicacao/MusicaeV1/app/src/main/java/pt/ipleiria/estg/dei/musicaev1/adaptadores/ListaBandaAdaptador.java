package pt.ipleiria.estg.dei.musicaev1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
/*
public class ListaBandaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Banda> bandas;


    public ListaBandaAdaptador(Context context, ArrayList<Banda> bandas) {
        this.context = context;
        this.bandas = bandas;
    }

    @Override
    public int getCount() { return bandas.size(); }

    @Override
    public Object getItem(int position) {
        return bandas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bandas.get(position).getIdbanda();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_banda, null);
        }

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(bandas.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView nome;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNomeBanda);
        }

        public void update(Banda banda){
            nome.setText(banda.getNome());
        }
    }


}
*/