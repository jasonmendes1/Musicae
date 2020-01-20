package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;


public interface BandasListener {
    void onRefreshBanda(ArrayList<Banda> Bandas);
}
