package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;

public interface BandasListener {
    void onRefreshListaBandas(ArrayList<Banda> listaBandas);
    void onUpdateListaBandas(Banda banda, int operacao);
}
