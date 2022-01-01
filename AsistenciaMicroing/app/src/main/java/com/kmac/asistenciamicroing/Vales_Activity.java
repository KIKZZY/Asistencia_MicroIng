package com.kmac.asistenciamicroing;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Vales_Activity extends AppCompatActivity {
    Spinner snnombre;
    EditText etfecha;
    EditText etcantidad;
    Button btnregistrar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference databaseReference;

    String Nombresel="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarfirebase();

        setContentView(R.layout.activity_vales);
        snnombre = findViewById(R.id.snNombreVales);
        obtenerNombres();
        etfecha = findViewById(R.id.etFechaVale);
        etcantidad = findViewById(R.id.etCantidad);
        btnregistrar = findViewById(R.id.RegistrarVale);


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrar();
            }
        });

    }


    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void obtenerNombres() {
        final List<Colaborador> nombresCol = new ArrayList<>();
        CollectionReference nombresRef = db.collection("Colaboradores");
        Query query = nombresRef.whereEqualTo("Estado", "1");
        db.collection("Colaboradores").whereEqualTo("Estado", "1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId().toString();
                                String nombre = document.getString("Nombre");
                                nombresCol.add(new Colaborador(id, nombre));
                            }

                            ArrayAdapter<Colaborador> arrayAdapter = new ArrayAdapter<>(Vales_Activity.this, android.R.layout.simple_dropdown_item_1line, nombresCol);
                            snnombre.setAdapter(arrayAdapter);

                            snnombre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Nombresel = parent.getItemAtPosition(position).toString();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }


                });
    }

    private void registrar() {

        Integer cantidad = etcantidad.getText().length();
        String nombreCol = Nombresel;
        String fechaVale = etfecha.getText().toString();
        Resources res = getResources();

        if (!nombreCol.equals("")&& !fechaVale.equals("")&& !cantidad.equals("")) {
            //
            Map<String, Object> map = new HashMap<>();
            map.put( "Nombre", nombreCol);
            map.put( "FechaVale", fechaVale);
            map.put( "Cantidad", cantidad);

            db.collection("Vales")
                    .add(map)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            //Resources res = getResources();
            Toast.makeText(this, res.getString(R.string.Registro_Exitoso), Toast.LENGTH_LONG).show();

            limpiar();

            //startActivity(intentMain);
        }else{
            //Resources res = getResources();
            Toast.makeText(this, res.getString(R.string.Campos_Vacios), Toast.LENGTH_LONG).show();
        }


    }

    private void limpiar() {
        etcantidad.setText("");
        snnombre.setPrompt("@string/Select");
        etfecha.setText("");

    }

    /*
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

    }*/


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
            case (R.id.Habilitar):
                Intent intenthabilitar= new Intent(this, HColaborador_Activity.class);
                startActivity(intenthabilitar);
                break;
            case (R.id.Asistencia):
                Intent intentasistencia= new Intent(this, MainActivity.class);
                startActivity(intentasistencia);
                break;
            case (R.id.Vales):
                Intent intentvales= new Intent(this, Vales_Activity.class);
                startActivity(intentvales);
                break;
            case (R.id.ListadoC):
                Intent intentlistado= new Intent(this, ListadoColaboradores_Activity.class);
                startActivity(intentlistado);
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