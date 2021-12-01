package com.kmac.asistenciamicroing;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.StringSearch;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RColaborador_Activity extends AppCompatActivity {


    EditText nombre;
    EditText idNumber;
    Spinner cargo;
    EditText fecha;
    Button btnRegistrar;

    FirebaseDatabase firebaseDatabase;
   FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference databaseReference;

    String Cargosel="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcolaborador);

        inicializarfirebase();

        nombre = findViewById(R.id.etNombreNuevoCol);
        idNumber = findViewById(R.id.etNumIde);
        cargo = findViewById(R.id.snCargo);
        obtenerCargos();
        fecha = findViewById(R.id.etFechaIngreso);
        btnRegistrar =findViewById(R.id.Registrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrar();
            }
        });



    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void obtenerCargos(){
        final List<Cargo> cargosC = new ArrayList<>();
        databaseReference.child("Cargos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String NomCargo = ds.child("Cargo").getValue().toString();
                        cargosC.add(new Cargo(id, NomCargo));
                    }
                    ArrayAdapter<Cargo> arrayAdapter = new ArrayAdapter<> (RColaborador_Activity.this, android.R.layout.simple_dropdown_item_1line, cargosC);
                    cargo.setAdapter(arrayAdapter);

                    cargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Cargosel = parent.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void  registrar(){
        String namecol = nombre.getText().toString();
        String identificacion = idNumber.getText().toString();
        String cargocol = Cargosel;
        String fechaIng = fecha.getText().toString();
        Resources res = getResources();


        if (!namecol.equals("")&& !cargocol.equals("")&& !identificacion.equals("")&& !fechaIng.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put( "Nombre", namecol);
            map.put( "Identificacion", identificacion);
            map.put( "Cargo", cargocol);
            map.put( "FechaIgreso", fechaIng);
            map.put( "Estado", "1");

            db.collection("Colaboradores")
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
            Toast.makeText(this, res.getString(R.string.Registro_Exitoso), Toast.LENGTH_LONG).show();
            limpiar();

            //startActivity(intentMain);
        }else{
            //Resources res = getResources();
            Toast.makeText(this, res.getString(R.string.Campos_Vacios), Toast.LENGTH_LONG).show();
        }


    }


    private void limpiar() {
        nombre.setText("");
        idNumber.setText("");
        cargo.setPrompt("@string/Select");
        fecha.setText("");

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