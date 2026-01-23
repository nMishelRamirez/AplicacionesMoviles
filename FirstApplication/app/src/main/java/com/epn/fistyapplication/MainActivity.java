package com.epn.fistyapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView resultadoNombre;
    EditText nombreEditText;
    public void buttonPress(View view){
        Log.i("Info", "Boton Presionado");
        nombreEditText = findViewById(R.id.editNombre);
        String nombreString = nombreEditText.getText().toString();
        resultadoNombre.setText(nombreString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultadoNombre = findViewById(R.id.tvResultadoNombre);
        resultadoNombre.setText("NOMBRE AQUÍ");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}