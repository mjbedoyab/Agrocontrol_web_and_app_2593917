package com.example.androidagro;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidagro.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TablaTareas extends AppCompatActivity {
    Config config;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_tabla_tareas);

        config = new Config(getApplicationContext()); // Asegúrate de inicializar tu clase Config antes de usarla

        tablaTarea(); // Llamada al método para cargar y mostrar las tareas
    }

    public void tablaTarea(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // Asegúrate de añadir los parámetros necesarios a tu URL si son requeridos por tu API
        String url = config.getEndpoint("APIenPHP-agricultura/joins/joinTarea.php") + "?id_agricultor=098&id_cultivo=101"; // Reemplaza X e Y con los valores apropiados

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray registrosArray = jsonObject.getJSONArray("registros");

                    TableLayout table = findViewById(R.id.tableLayout);
                    for (int i = 0; i < registrosArray.length(); i++) {
                        JSONObject client = registrosArray.getJSONObject(i);

                        String titulo = client.getString("titulo");
                        String descripcion = client.getString("descripcion");
                        String fecha_fin = client.getString("fecha_fin");
                        String estado = client.getString("estado");

                        TableRow row = new TableRow(TablaTareas.this);
                        TextView tvTitulo = new TextView(TablaTareas.this);
                        TextView tvDescripcion = new TextView(TablaTareas.this);
                        TextView tvFechaFin = new TextView(TablaTareas.this);
                        TextView tvEstado = new TextView(TablaTareas.this);

                        tvTitulo.setText(titulo);
                        tvDescripcion.setText(descripcion);
                        tvFechaFin.setText(fecha_fin);
                        tvEstado.setText(estado);

                        // Aquí configuramos el diseño de las TextViews como desees
                        // Por ejemplo: tvTitulo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

                        row.addView(tvTitulo);
                        row.addView(tvDescripcion);
                        row.addView(tvFechaFin);
                        row.addView(tvEstado);

                        table.addView(row);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

