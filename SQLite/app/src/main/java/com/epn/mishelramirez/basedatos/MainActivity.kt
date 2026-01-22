package com.epn.mishelramirez.basedatos

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import com.epn.mishelramirez.basedatos.dao.UsuarioDao
import com.epn.mishelramirez.basedatos.modelo.Usuario

class MainActivity : AppCompatActivity() {
    private lateinit var usuarioDao: UsuarioDao
    private lateinit var listView: ListView
    private lateinit var edtNombres: EditText
    private lateinit var edtApellidos: EditText
    private lateinit var edtCedula: EditText

    private lateinit var adapter: UsuarioAdapter
    private lateinit var listaUsuarios: MutableList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DAO
        usuarioDao = UsuarioDao(this)

        // Vistas
        edtNombres = findViewById(R.id.edtNombres)
        edtApellidos = findViewById(R.id.edtApellidos)
        edtCedula = findViewById(R.id.edtCedula)
        listView = findViewById(R.id.listUsuarios)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Evento Guardar
        btnGuardar.setOnClickListener {
            guardarUsuario()
        }

        // Cargar datos iniciales
        cargarLista()
    }

    private fun guardarUsuario() {
        val nombres = edtNombres.text.toString()
        val apellidos = edtApellidos.text.toString()
        val cedula = edtCedula.text.toString()

        if (nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Insertar en SQLite
        usuarioDao.insertar(
            Usuario(
                nombres = nombres,
                apellidos = apellidos,
                cedula = cedula
            )
        )

        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show()
        limpiarCampos()
        cargarLista()
    }

    private fun cargarLista() {
        listaUsuarios = usuarioDao.listar().toMutableList()
        adapter = UsuarioAdapter(this, listaUsuarios, usuarioDao)
        listView.adapter = adapter
    }

    private fun limpiarCampos() {
        edtNombres.text.clear()
        edtApellidos.text.clear()
        edtCedula.text.clear()
    }
}
