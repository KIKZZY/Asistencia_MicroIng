package com.kmac.asistenciamicroing;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void goToMain(View view){
        etName = findViewById(R.id.etName);
        String name =etName.getText().toString();

        Intent intentMain = new Intent(this, MainActivity.class);

        intentMain.putExtra("user", name);

        startActivity(intentMain);
    }
}