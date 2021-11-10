package com.kmac.asistenciamicroing;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.view.View;
import android.content.Intent;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void goToMain(View view){
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);

        Resources res = getResources();

        String name =etName.getText().toString();
        String pass =etPassword.getText().toString();

        if (!name.equals("")&& !pass.equals((""))) {
            Intent intentMain = new Intent(this, MainActivity.class);

            intentMain.putExtra("user", name);

            startActivity(intentMain);
        }else{
            Toast.makeText(this, res.getString(R.string.Campos_Vacios), Toast.LENGTH_LONG).show();
        }

    }
}