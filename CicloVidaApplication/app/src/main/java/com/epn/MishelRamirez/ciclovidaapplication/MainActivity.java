package com.epn.MishelRamirez.ciclovidaapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText valor1;
    private EditText valor2;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("CicloDeVida", "onCreate ejecutando");

        // Inicializacion
        valor1 = findViewById(R.id.editTextValor1);
        valor2 = findViewById(R.id.editTextValor2);
        resultado = findViewById(R.id.textMostrarResultado);
    }

    public void buttonPressSuma(View view) {
        Log.i("Info", "Botón de suma presionado");

        String texto1 = valor1.getText().toString();
        String texto2 = valor2.getText().toString();

        if (texto1.isEmpty() || texto2.isEmpty()) {
            Toast.makeText(this, "Ingrese ambos valores", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(texto1);
        double num2 = Double.parseDouble(texto2);
        double suma = num1 + num2;

        resultado.setText("Resultado: " + suma);
    }

    public void buttonPressDividir(View view) {
        Log.i("Info", "Botón de división presionado");

        String texto1 = valor1.getText().toString();
        String texto2 = valor2.getText().toString();

        if (texto1.isEmpty() || texto2.isEmpty()) {
            Toast.makeText(this, "Ingrese ambos valores", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(texto1);
        double num2 = Double.parseDouble(texto2);

        if (num2 == 0) {
            Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
            return;
        }

        double division = num1 / num2;
        resultado.setText("Resultado: " + division);
    }

    // Métodos del ciclo de vida
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CicloDeVida", "onStart ejecutando");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CicloDeVida", "onResume ejecutando");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CicloDeVida", "onPause ejecutando");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CicloDeVida", "onStop ejecutando");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CicloDeVida", "onDestroy ejecutando");
    }
}
