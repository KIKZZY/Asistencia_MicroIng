package com.kmac.asistenciamicroing;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;
import android.content.Intent;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private SharedPreferences settings; // SP
    private boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences("id", Context.MODE_PRIVATE); // SP
    }

    public void checkUser(String nombre, String contrasena) {

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Usuario");
        myRef.child("u_" + nombre).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Usuario value = snapshot.getValue(Usuario.class);
                if (value != null) {
                    String saved_password = value.getContrasena();
                    if (saved_password.equals(contrasena)) {
                        success = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(LoginActivity.this, "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToMain(View view) {
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        // pruebaLectura(name);

        Intent intentMain = new Intent(this, MainActivity.class);

        intentMain.putExtra("user", name);

        checkUser(name, password);
        if (success) {
            // SP
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("id", 1);
            editor.putString("usuario", name);
            editor.commit();
            //
            startActivity(intentMain);
        } else {
            Toast.makeText(this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
        }

    }

}