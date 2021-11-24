package com.kmac.asistenciamicroing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class IColaborador_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icolaborador);

        Button btnInhabilitar =findViewById(R.id.btnInhabilitar);
        btnInhabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InhabilitarColaborador();
            }
        });
    }

    private void InhabilitarColaborador() {
        CheckBox inhabilitar = findViewById(R.id.cbInhabilitar);
        if (inhabilitar.isChecked()){

        }
    }



//Metodo para agregar el menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

//Metodo para navegar segun item seleccionado

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