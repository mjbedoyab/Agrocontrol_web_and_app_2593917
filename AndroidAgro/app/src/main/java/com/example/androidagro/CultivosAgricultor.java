package com.example.androidagro;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidagro.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CultivosAgricultor extends AppCompatActivity {
    Config config;
    RecyclerView recycler;
    AdaptadorCultivos adaptador;
    List<Cultivos> lista;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos);
        recycler = findViewById(R.id.recycler_cultivos);
        config = new Config(getApplicationContext());
        traerCultivos();
    }

    public void traerCultivos() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = config.getEndpoint("APIenPHP-agricultura/cultivos/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("El servidor POST responde OK");
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray registrosArray = jsonObject.getJSONArray("registros");

                    lista = new ArrayList<>();
                    for (int i = 0; i < registrosArray.length(); i++) {
                        JSONObject client = registrosArray.getJSONObject(i);
                        String id_cultivo = client.getString("id_cultivo");
                        String nombre = client.getString("nombre");
                        String descripcion = client.getString("descripcion");
                        String img = client.getString("img");
                        lista.add(new Cultivos(img, id_cultivo, nombre, descripcion));
                        System.out.println("Id Cultivo Inicio: "+id_cultivo);
                    }
                    adaptador = new AdaptadorCultivos(lista);
                    recycler.setAdapter(adaptador);
                    recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    System.out.println(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor responde con un error:");
                System.out.println(error.getMessage());
            }
        });
        queue.add(solicitud);
    }
}
