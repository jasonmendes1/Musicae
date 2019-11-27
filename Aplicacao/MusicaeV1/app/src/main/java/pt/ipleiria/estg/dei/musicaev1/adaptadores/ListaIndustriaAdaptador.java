package pt.ipleiria.estg.dei.musicaev1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Habilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.Industria;

public class ListaIndustriaAdaptador extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Industria> industrias;

    public ListaIndustriaAdaptador(Context context, ArrayList<Industria> industrias) {
        this.context = context;
        this.industrias = industrias;
    }

    @Override
    public int getCount() { return industrias.size(); }

    @Override
    public Object getItem(int position) {
        return industrias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return industrias.get(position).getIdIndustria();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_industria, null);
        }

        ListaIndustriaAdaptador.ViewHolderLista viewHolder = (ListaIndustriaAdaptador.ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ListaIndustriaAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(industrias.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView tipo;

        public ViewHolderLista(View convertView){
            tipo = convertView.findViewById(R.id.tvIndustria);
        }

        public void update(Industria industria){
            tipo.setText(industria.getTipo());
        }
    }
}
