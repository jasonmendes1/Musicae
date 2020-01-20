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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

    private TextView tvUsername, tvId, tvNome, tvEmail, tvDataNasc, tvNrTelemovel, tvHabilidade, tvGenero, tvSexo, tvLocalidade;
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
        mQueue = Volley.newRequestQueue(getContext());

        tvUsername = rootView.findViewById(R.id.tvUsername);
        tvId = rootView.findViewById(R.id.tvID);
        tvNome = rootView.findViewById(R.id.tvName);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        tvDataNasc = rootView.findViewById(R.id.tvNumber);
        tvNrTelemovel = rootView.findViewById(R.id.tvBirthDate);
        tvHabilidade = rootView.findViewById(R.id.tvInstrument);
        tvGenero = rootView.findViewById(R.id.tvGenres);
        tvSexo = rootView.findViewById(R.id.tvGender);
        tvLocalidade = rootView.findViewById(R.id.tvCity);


        // NÃO ESQUECER DE METER O IDUSER A FUNCIONAR
        IdUser = Singleton.getInstance(getContext()).getIdUser();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + "/" + IdUser, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("-->RESPOSTA WI ");
                        try {
                            JSONObject profile = response.getJSONObject(0);

                            String UserUsername = profile.getString("UserUsername");
                            String HabilidadeNome = profile.getString("HabilidadeNome");
                            String GeneroNome = profile.getString("GeneroNome");
                            String ProfileNome = profile.getString("ProfileNome");
                            String ProfileSexo = profile.getString("ProfileSexo");
                            String ProfileLocalidade = profile.getString("ProfileLocalidade");
                            String ProfileFoto = profile.getString("ProfileFoto");
                            String UserEmail = profile.getString("UserEmail");

                            tvUsername.setText(UserUsername);
                            tvId.setText("#"+ IdUser);
                            tvNome.setText(ProfileNome);
                            tvEmail.setText(UserEmail);
                            tvHabilidade.setText(HabilidadeNome);
                            tvGenero.setText(GeneroNome);
                            tvSexo.setText(ProfileSexo);
                            tvLocalidade.setText(ProfileLocalidade);

                            /*
                            tvUsername = rootView.findViewById(R.id.tvUsername);
                            tvId = rootView.findViewById(R.id.tvID);
                            tvNome = rootView.findViewById(R.id.tvName);
                            tvEmail = rootView.findViewById(R.id.tvEmail);
                            tvDataNasc = rootView.findViewById(R.id.tvNumber);
                            */
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

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