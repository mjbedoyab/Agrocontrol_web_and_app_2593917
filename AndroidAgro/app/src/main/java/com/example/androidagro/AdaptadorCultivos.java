package com.example.androidagro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdaptadorCultivos extends RecyclerView.Adapter<AdaptadorCultivos.ViewHolder> {

    List<Cultivos> ListaCultivos;

    public AdaptadorCultivos(List<Cultivos> lista){

        this.ListaCultivos = lista;
    }



    @NonNull
    @Override
    public AdaptadorCultivos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cultivos, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCultivos.ViewHolder holder, int position) {
        Cultivos temporal = ListaCultivos.get(position);
        holder.cargarDatos(temporal);
    }

    @Override
    public int getItemCount() {

        return ListaCultivos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView id_cultivo;
        TextView nombre;
        TextView descripcion;
        Context contexto;
        FloatingActionButton btnDetalle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            img = itemView.findViewById(R.id.img);
            id_cultivo = itemView.findViewById(R.id.id_cultivo);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);

        }
        public  void cargarDatos(Cultivos datos){
            //img.setText(datos.getImg());
            id_cultivo.setText(datos.getId_cultivo());
            nombre.setText(datos.getNombre());
            descripcion.setText(datos.getDescripcion());
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(contexto, AsignarTareaCultivo.class);
                    //Toast.makeText( contexto, datos.getNombre(), Toast.LENGTH_LONG ).show();
                    contexto.startActivity(intent);
                }
            });
        }
    }
}
