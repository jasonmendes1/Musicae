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

public class ListaHabilidadeAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Habilidade> habilidades;

    public ListaHabilidadeAdaptador(Context context, ArrayList<Habilidade> habilidades) {
        this.context = context;
        this.habilidades = habilidades;
    }

    @Override
    public int getCount() { return habilidades.size(); }

    @Override
    public Object getItem(int position) {
        return habilidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return habilidades.get(position).getIdHabilidade();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_habilidade, null);
        }

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(habilidades.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView tipo;

        public ViewHolderLista(View convertView){
            tipo = convertView.findViewById(R.id.tvHabilidade);
        }

        public void update(Habilidade habilidade){
            tipo.setText(habilidade.getTipo());
        }
    }
}
