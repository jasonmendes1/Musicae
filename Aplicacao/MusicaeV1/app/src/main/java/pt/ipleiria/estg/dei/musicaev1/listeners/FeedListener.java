package pt.ipleiria.estg.dei.musicaev1.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.musicaev1.modelos.Feed;

public interface FeedListener {
    void onRefreshListaBandasFeed(ArrayList<Feed> listaFeed);
    void onUpdateListaBandasFeed(Feed feed, int operacao);
}
