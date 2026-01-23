package com.epn.mishelramirez.listview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Referencia al ListView
        val listView = findViewById<ListView>(R.id.listViewProductos)

        // Datos
        val productos = arrayOf(
            "Laptop",
            "Mouse",
            "Teclado",
            "Monitor"
        )

        // Adaptador
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productos
        )

        // Asignacion del  adaptador
        listView.adapter = adapter

        // EVENTOS
        // listView.setOnClickListener { }

    }

}
