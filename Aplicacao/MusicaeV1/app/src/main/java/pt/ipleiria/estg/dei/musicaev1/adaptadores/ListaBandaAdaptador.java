package pt.ipleiria.estg.dei.musicaev1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaMembro;

public class ListaBandaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<BandaMembro> bandaMembros;


    public ListaBandaAdaptador(Context context, ArrayList<BandaMembro> bandaMembro) {
        this.context = context;
        this.bandaMembros = bandaMembro;
    }

    @Override
    public int getCount() {
        return bandaMembros.size();
    }

    @Override
    public Object getItem(int position) {
        return bandaMembros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bandaMembros.get(position).getId();
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

        viewHolder.update(bandaMembros.get(position));
        return convertView;
    }

    public void refresh(ArrayList<BandaMembro> bandaMembros) {
        this.bandaMembros = bandaMembros;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView nome;
        private TextView instrumento;
        private TextView dataEntrada;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNome);
            instrumento = convertView.findViewById(R.id.tvInstrumento);
            dataEntrada = convertView.findViewById(R.id.tvDataEntrada);
        }

        public void update(BandaMembro bandaMembros){
            nome.setText(bandaMembros.getBandaNome());
            instrumento.setText(bandaMembros.getHabilidadeNome());
            dataEntrada.setText(bandaMembros.getDataentrada());
        }
    }
}
