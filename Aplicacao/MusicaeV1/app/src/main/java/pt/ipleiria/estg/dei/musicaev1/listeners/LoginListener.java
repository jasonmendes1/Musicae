package pt.ipleiria.estg.dei.musicaev1.listeners;

import org.json.JSONArray;
import org.json.JSONObject;

public interface LoginListener {
    void onRefreshLogin(JSONArray response);
}
