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
import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;

public class ListaBandaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Banda> bandas;


    public ListaBandaAdaptador(Context context, ArrayList<Banda> bandas) {
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

    public void refresh(ArrayList<Banda> listaBandas) {
        this.bandas = listaBandas;
        notifyDataSetChanged();
    }

    private class ViewHolderLista{
        private TextView nome;
        private TextView genero;
        private TextView localizacao;
        private TextView contacto;
        private ImageView capa;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNome);
            genero = convertView.findViewById(R.id.tvGenero);
            localizacao = convertView.findViewById(R.id.tvLocalizacao);
            contacto = convertView.findViewById(R.id.tvContacto);
            capa = convertView.findViewById(R.id.imageViewCapa);
        }

        public void update(Banda banda){
            nome.setText(banda.getNome());
            genero.setText(banda.getGenero());
            localizacao.setText(banda.getLocalizacao());
            contacto.setText(""+banda.getContacto());
            Glide.with(context)
                    .load(banda.getCapa())
                    .placeholder(R.drawable.banner)
                    .thumbnail(0f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(capa);
        }
    }
}
