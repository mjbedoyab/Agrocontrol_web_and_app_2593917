package com.example.androidagro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsignarTareaCultivo extends AppCompatActivity {

    TextView nombre_cultivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_cultivo);
        nombre_cultivo = findViewById(R.id.nombre_cultivo);

        String nameCultivo = getIntent().getStringExtra("nombre");
        nombre_cultivo.setText(nameCultivo);

    }
}
