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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
public class ListadoColaboradores_Activity extends AppCompatActivity {

    Spinner nombres;
    ListView Datos;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference databaseReference;
    String Nombresel="";
    String UidSel;
    String cargosel;
    String Idesel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_colaboradores);
        inicializarfirebase();
        nombres = findViewById(R.id.spListarCol);
        obtenerNombres();
        Datos= findViewById(R.id.DatosC);
    }

    private void obtenerNombres() {
        final List<Colaborador> nombresCol = new ArrayList<>();
        CollectionReference nombresRef = db.collection("Colaboradores");
        //Query query = nombresRef.whereEqualTo("Estado", "1");
        db.collection("Colaboradores").whereEqualTo("Estado", "1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId().toString();
                                String nombre = document.getString("Nombre");
                                String iden= document.getString("Identificacion");
                                String car = document.getString("Cargo");
                                nombresCol.add(new Colaborador(id, nombre, iden, car));
                            }
                            ArrayAdapter<Colaborador> arrayAdapter = new ArrayAdapter<>(ListadoColaboradores_Activity.this, android.R.layout.simple_dropdown_item_1line, nombresCol);

                            nombres.setAdapter(arrayAdapter);
                            nombres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Nombresel = parent.getItemAtPosition(position).toString();
                                    UidSel= nombresCol.get(position).getUid();
                                    cargosel=nombresCol.get(position).getCargo();
                                    Idesel=nombresCol.get(position).getIdentificacion();
                                    listarColaboradores();



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

    private void listarColaboradores() {
        List<String> listPersona = new ArrayList<>();

                    listPersona.add("Nombre: "+Nombresel);
                    listPersona.add("Identificación: "+Idesel);
                    listPersona.add("Cargo: "+cargosel);

                    ArrayAdapter<String> arrayAdapterPersona = new ArrayAdapter<String>(ListadoColaboradores_Activity.this, android.R.layout.simple_dropdown_item_1line, listPersona);

                    Datos.setAdapter(arrayAdapterPersona);
    }


    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    @Override
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
