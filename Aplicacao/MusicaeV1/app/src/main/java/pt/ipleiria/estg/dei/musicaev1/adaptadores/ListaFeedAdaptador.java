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
import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;

public class ListaFeedAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Feed> bandasFeed;


    public ListaFeedAdaptador(Context context, ArrayList<Feed> bandasFeed) {
        this.context = context;
        this.bandasFeed = bandasFeed;
    }

    @Override
    public int getCount() {
        return bandasFeed.size();
    }

    @Override
    public Object getItem(int position) {
        return bandasFeed.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bandasFeed.get(position).getId();
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

        viewHolder.update(bandasFeed.get(position));
        return convertView;
    }

    public void refresh(ArrayList<Feed> listaFeed) {
        this.bandasFeed = listaFeed;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView nome;
        private TextView instrumento;
        private TextView experiencia;
        private TextView compromisso;
        private ImageView logo;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNome);
            instrumento = convertView.findViewById(R.id.tvInstrumento);
            experiencia = convertView.findViewById(R.id.tvExperiencia);
            compromisso = convertView.findViewById(R.id.tvCompromisso);
            logo = convertView.findViewById(R.id.ivFeed);
        }

        public void update(Feed feed){
            nome.setText(feed.getNome());
            instrumento.setText(feed.getInstrumento());
            experiencia.setText(feed.getExperiencia());
            compromisso.setText(feed.getCompromisso());
            Glide.with(context)
                    .load(feed.getLogo())
                    .placeholder(R.drawable.banner)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(logo);
        }
    }


}
