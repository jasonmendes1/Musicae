package pt.ipleiria.estg.dei.musicaev1.vistas;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.musicaev1.MenuMainActivity;
import pt.ipleiria.estg.dei.musicaev1.R;
import pt.ipleiria.estg.dei.musicaev1.modelos.Perfil;
import pt.ipleiria.estg.dei.musicaev1.modelos.Singleton;

public class ProfileFragment extends Fragment {

    private TextView tvUsername, tvId, tvNome, tvEmail, tvDataNasc, tvNrTelemovel;
    private Perfil perfil;
    private SharedPreferences sharedPreferences;
    private Button buttonEditar, buttonBanda;
    private int IdUser;
    private String url = "http://192.168.1.68/MusicaeWeb/backend/web/v1/user/profile";
    private RequestQueue mQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = rootView.findViewById(R.id.tvUsername);
        tvId = rootView.findViewById(R.id.tvID);
        tvNome = rootView.findViewById(R.id.tvName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvDataNasc = rootView.findViewById(R.id.tvNumber);
        tvNrTelemovel = rootView.findViewById(R.id.tvBirthDate);

        // NÃO ESQUECER DE METER O IDUSER A FUNCIONAR
        //IdUser = Singleton.getInstance(getContext()).getIdUser();
        IdUser = 5;
        System.out.println("-->w url: " + url + "/" + IdUser);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/" + IdUser, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("-->w estamos dentro do onResponse wi todos doidos ");
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                System.out.println("-->w " + response.toString());
                                JSONObject profile = response.getJSONObject(i);

                                String UserUsername = profile.getString("UserUsername");
                                String HabilidadeNome = profile.getString("HabilidadeNome");
                                String GeneroNome = profile.getString("GeneroNome");
                                String ProfileNome = profile.getString("ProfileNome");
                                String ProfileSexo = profile.getString("ProfileSexo");
                                String ProfileLocalidade = profile.getString("ProfileLocalidade");
                                String ProfileFoto = profile.getString("ProfileFoto");
                                String UserEmail = profile.getString("UserEmail");


                                tvUsername.append(UserUsername);
                                tvId.append(IdUser + "");
                                tvNome.append(ProfileNome);
                                tvEmail.append(UserEmail);

                                /*
                                tvUsername = rootView.findViewById(R.id.tvUsername);
                                tvId = rootView.findViewById(R.id.tvID);
                                tvNome = rootView.findViewById(R.id.tvName);
                                tvEmail = rootView.findViewById(R.id.tvEmail);
                                tvDataNasc = rootView.findViewById(R.id.tvNumber);
                                */
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("-->w Error burro do caralho: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("-->w Error burro do caralho: " + error.getMessage());
            }
        });
        //mQueue.add(request);

        buttonEditar = rootView.findViewById(R.id.btnEditProfile);
        buttonBanda = rootView.findViewById(R.id.btnBanda);

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonBanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return rootView;
    }

    private void mostrarPerfil(Perfil perfil){
        tvUsername.setText(perfil.getNome());
        tvId.setText(perfil.getId());
        tvNome.setText(perfil.getNome());
        tvDataNasc.setText(perfil.getDatanasc());
        tvNrTelemovel.setText(perfil.getNrtelemovel());
    }

    private Perfil editarPerfil(){
        perfil.setNome(perfil.getNome());
        perfil.setSexo(perfil.getSexo());
        perfil.setLocalidade(perfil.getLocalidade());
        perfil.setNrtelemovel(perfil.getNrtelemovel());
        perfil.setDatanasc(perfil.getDatanasc());

        return perfil;
    }

}