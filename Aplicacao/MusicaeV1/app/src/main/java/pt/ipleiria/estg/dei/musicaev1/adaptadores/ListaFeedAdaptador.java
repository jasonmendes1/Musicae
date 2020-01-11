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
import pt.ipleiria.estg.dei.musicaev1.modelos.FeedModel;

public class ListaFeedAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<FeedModel> bandas;


    public ListaFeedAdaptador(Context context, ArrayList<FeedModel> bandas) {
        this.context = context;
        this.bandas = bandas;
    }

    @Override
    public int getCount() {
        return bandas.size();
    }

    @Override
    public Object getItem(int position) {
        return bandas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bandas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_feed, null);
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
        private TextView instrumento;
        private TextView experiencia;
        private TextView compromisso;
        private ImageView capa;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNome);
            instrumento = convertView.findViewById(R.id.tvInstrumento);
            experiencia = convertView.findViewById(R.id.tvExperiencia);
            compromisso = convertView.findViewById(R.id.tvCompromisso);
            capa = convertView.findViewById(R.id.ivBanda);
        }

        public void update(FeedModel banda){
            nome.setText(banda.getNome());
            instrumento.setText(banda.getInstrumento());
            experiencia.setText(banda.getExperiencia());
            compromisso.setText(banda.getCompromisso());
            Glide.with(context)
                    .load(banda.getCapa())
                    .placeholder(R.drawable.banner)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(capa);
        }
    }


}
