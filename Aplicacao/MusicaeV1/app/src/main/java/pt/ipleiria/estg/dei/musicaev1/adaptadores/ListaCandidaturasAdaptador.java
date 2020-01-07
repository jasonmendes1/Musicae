package pt.ipleiria.estg.dei.musicaev1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.CandidaturaModel;
import pt.ipleiria.estg.dei.musicaev1.modelos.CandidaturaModel;
import pt.ipleiria.estg.dei.musicaev1.modelos.FeedModel;

public class ListaCandidaturasAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<CandidaturaModel> candidaturas;


    public ListaCandidaturasAdaptador(Context context, ArrayList<CandidaturaModel> candidaturas) {
        this.context = context;
        this.candidaturas = candidaturas;
    }

    @Override
    public int getCount() {
        return candidaturas.size();
    }

    @Override
    public Object getItem(int position) {
        return candidaturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return candidaturas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_lista_candidatura, null);
        }

        ViewHolderLista viewHolder = (ViewHolderLista)convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(candidaturas.get(position));
        return convertView;
    }

    private class ViewHolderLista{
        private TextView nome;
        private TextView instrumento;
        private ImageView capa;

        public ViewHolderLista(View convertView){
            nome = convertView.findViewById(R.id.tvNomeCandidatura);
            instrumento = convertView.findViewById(R.id.tvSkillCandidatura);
            capa = convertView.findViewById(R.id.ivCandidatura);
        }

        public void update(CandidaturaModel candidatura){
            nome.setText(candidatura.getNome());
            instrumento.setText(candidatura.getInstrumento());
            capa.setImageResource(candidatura.getCapa());
        }
    }
}
