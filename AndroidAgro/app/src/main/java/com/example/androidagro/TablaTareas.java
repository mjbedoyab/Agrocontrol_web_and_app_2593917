package com.example.androidagro;

<<<<<<< HEAD
import android.os.Bundle;
import android.widget.CheckBox;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
>>>>>>> origin/garcia
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
<<<<<<< HEAD

public class TablaTareas extends AppCompatActivity {
    Config config;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaTareas extends AppCompatActivity {
    Config config;
    String id_agricultor;
    String id_cultivo;
    String nombre;

    TextView nombre_cultivo;

    CheckBox check[];
    String id_tareas[];

    String id_tarea;
    String tarea;

    List<String> tareasSeleccionadas = new ArrayList<>();
>>>>>>> origin/garcia

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_tabla_tareas);
<<<<<<< HEAD
=======
        nombre_cultivo=findViewById(R.id.nombre_cultivo);

        Intent intent = getIntent();
        id_agricultor=intent.getStringExtra("id_agricultor");
        id_cultivo=intent.getStringExtra("id_cultivo");
         nombre=intent.getStringExtra("nombre");
        nombre_cultivo.setText(nombre);
        System.out.println("nombre agri: "+id_agricultor);
        System.out.println("nombre cul: "+id_cultivo);
>>>>>>> origin/garcia

        config = new Config(getApplicationContext()); // Asegúrate de inicializar tu clase Config antes de usarla

        tablaTarea(); // Llamada al método para cargar y mostrar las tareas
    }

    public void tablaTarea(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // Asegúrate de añadir los parámetros necesarios a tu URL si son requeridos por tu API
<<<<<<< HEAD
        String url = config.getEndpoint("APIenPHP-agricultura/joins/joinTarea.php") + "?id_agricultor=098&id_cultivo=101"; // Reemplaza X e Y con los valores apropiados

        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
=======
        String url = config.getEndpoint("APIenPHP-agricultura/joins/joinTarea.php") ; // Reemplaza X e Y con los valores apropiados

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
>>>>>>> origin/garcia
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray registrosArray = jsonObject.getJSONArray("registros");

<<<<<<< HEAD
=======
                    check = new CheckBox[registrosArray.length()];
                    id_tareas =  new String[registrosArray.length()];
>>>>>>> origin/garcia
                    TableLayout table = findViewById(R.id.tableLayout);
                    for (int i = 0; i < registrosArray.length(); i++) {
                        JSONObject client = registrosArray.getJSONObject(i);

<<<<<<< HEAD
=======
                        id_tarea = client.getString("id_tarea");

                        id_tareas[i] = id_tarea;

                        final int finalI = i;

>>>>>>> origin/garcia
                        String titulo = client.getString("titulo");
                        String descripcion = client.getString("descripcion");
                        String fecha_fin = client.getString("fecha_fin");
                        String estado = client.getString("estado");

<<<<<<< HEAD
=======
                        check[i]=new CheckBox(getApplicationContext()) ;
                        //check[i].setBackgroundTint("")
>>>>>>> origin/garcia
                        TableRow row = new TableRow(TablaTareas.this);
                        TextView tvTitulo = new TextView(TablaTareas.this);
                        TextView tvDescripcion = new TextView(TablaTareas.this);
                        TextView tvFechaFin = new TextView(TablaTareas.this);
                        TextView tvEstado = new TextView(TablaTareas.this);

                        tvTitulo.setText(titulo);
                        tvDescripcion.setText(descripcion);
                        tvFechaFin.setText(fecha_fin);
                        tvEstado.setText(estado);

<<<<<<< HEAD
                        // Aquí configuramos el diseño de las TextViews como desees
                        // Por ejemplo: tvTitulo.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
=======

>>>>>>> origin/garcia

                        row.addView(tvTitulo);
                        row.addView(tvDescripcion);
                        row.addView(tvFechaFin);
<<<<<<< HEAD
                        row.addView(tvEstado);
=======

                        if(!estado.equals("Finalizado")){
                            row.addView(check[i]);

                            // Agrega el ID de la tarea a la lista cuando se marca la casilla de verificación
                            check[finalI].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        tareasSeleccionadas.add(id_tareas[finalI]);
                                    } else {
                                        tareasSeleccionadas.remove(id_tareas[finalI]);
                                    }
                                }
                            });

                        }else{
                            row.addView(tvEstado);
                        }


>>>>>>> origin/garcia

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
<<<<<<< HEAD
        });
        queue.add(solicitud);
    }

=======
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_agricultor", id_agricultor);
                params.put("id_cultivo", id_cultivo);



                return params;
            }
        };
        queue.add(solicitud);
    }

    public void SubirEstado(View v){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = config.getEndpoint("APIenPHP-agricultura/joins/updateTarea.php");
        for (String tarea : tareasSeleccionadas) {
            StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("estas son las ids: "+tarea);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("El servidor responde con un error:");
                    System.out.println(error.getMessage());
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("id_tarea", tarea);
                    params.put("estado", "Finalizado");

                    return params;
                }
            };
            queue.add(solicitud);
        }
    }



>>>>>>> origin/garcia
}

