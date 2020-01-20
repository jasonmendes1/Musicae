package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Banda;

public interface BandasListener {
        void onRefreshBanda(ArrayList<Banda> listabanda);
        void onUpdateListaBandasBD(Banda banda, int operacao);

}
