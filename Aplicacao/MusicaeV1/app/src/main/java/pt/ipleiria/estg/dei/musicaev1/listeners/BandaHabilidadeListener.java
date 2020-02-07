package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaHabilidade;
import pt.ipleiria.estg.dei.musicaev1.modelos.BandaMembro;


public interface BandaHabilidadeListener {
    void onRefreshBandaHabilidades(ArrayList<BandaMembro> MinhasBandas);
}
