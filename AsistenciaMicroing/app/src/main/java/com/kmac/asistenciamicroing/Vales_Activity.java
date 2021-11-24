package com.kmac.asistenciamicroing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Vales_Activity extends AppCompatActivity {
    Spinner snnombre;
    EditText etfecha;
    EditText etcantidad;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vales);
        snnombre = findViewById(R.id.snNombreVales);
        etfecha = findViewById(R.id.etFechaVale);
        etcantidad = findViewById(R.id.etCantidad);
        inicializarfirebase();


    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void Registrar(View view){
        Colaborador c = new Colaborador();
        c.setNombre(UUID.randomUUID().toString());
        //String nombre = snnombre.getText().toString;
        //Date fecha = Date.from()
        Integer cantidad= Integer.valueOf(etcantidad.getText().toString());


        //c.setNombre(nombre);
        //c.setFechaVale(fecha);
        c.setCantidad(cantidad);
        databaseReference.child("Colaborador").child(c.getNombre()).setValue(c);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.Ingresar):
                Intent intentingresar= new Intent(this, RColaborador_Activity.class);
                startActivity(intentingresar);
                break;
            case (R.id.Retirar):
                Intent intentretirar= new Intent(this, IColaborador_Activity.class);
                startActivity(intentretirar);
                break;
            case (R.id.Asistencia):
                Intent intentasistencia= new Intent(this, MainActivity.class);
                startActivity(intentasistencia);
                break;
            case (R.id.Vales):
                Intent intentvales= new Intent(this, Vales_Activity.class);
                startActivity(intentvales);
                break;
            case (R.id.logout):
                Intent intentlogout= new Intent(this, LoginActivity.class);
                startActivity(intentlogout);
                break;

            default:break;
            //throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

}