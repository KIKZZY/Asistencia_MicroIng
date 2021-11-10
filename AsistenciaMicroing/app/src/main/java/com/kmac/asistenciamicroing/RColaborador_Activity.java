package com.kmac.asistenciamicroing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RColaborador_Activity extends AppCompatActivity {

    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcolaborador);

        spinner1= (Spinner) findViewById(R.id.snCargo);

        String [] cargo= {"Supervisor", "Oficial", "Ayudante Práctico", "Ayudante", "Subcontratista"};

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cargo);
        spinner1.setAdapter(adapter);
    }
}