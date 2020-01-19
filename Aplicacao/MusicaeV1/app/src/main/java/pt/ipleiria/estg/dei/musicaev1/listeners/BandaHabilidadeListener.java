package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;


public interface BandaHabilidadeListener {
    void onRefreshBandaHabilidades(ArrayList<BandaHabilidade> MinhasBandas);
}
