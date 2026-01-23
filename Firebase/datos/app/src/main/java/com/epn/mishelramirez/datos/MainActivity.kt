package com.epn.mishelramirez.datos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QueryDocumentSnapshot;
class MainActivity : AppCompatActivity() {

    // Variables
    private lateinit var edtNombres: EditText
    private lateinit var edtApellidos: EditText
    private lateinit var edtCedula: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnBuscar: Button
    private lateinit var btnEliminar: Button
    private lateinit var listViewDatos: ListView

    private lateinit var db: FirebaseFirestore
    private lateinit var lista: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    private var documentoSeleccionado: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNombres = findViewById(R.id.edtNombres)
        edtApellidos = findViewById(R.id.edtApellidos)
        edtCedula = findViewById(R.id.edtCedula)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnEliminar = findViewById(R.id.btnEliminar)
        listViewDatos = findViewById(R.id.listViewDatos)

        db = FirebaseFirestore.getInstance()
        lista = ArrayList()
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_single_choice,
            lista
        )
        listViewDatos.adapter = adapter
        listViewDatos.choiceMode = ListView.CHOICE_MODE_SINGLE

        btnGuardar.setOnClickListener { guardar() }
        btnBuscar.setOnClickListener { listar() }
        btnEliminar.setOnClickListener { eliminar() }

        listViewDatos.setOnItemClickListener { _, _, position, _ ->
            documentoSeleccionado = lista[position].split(" - ")[2]
        }
    }
    private fun guardar() {
        val nombres = edtNombres.text.toString().trim()
        val apellidos = edtApellidos.text.toString().trim()
        val cedula = edtCedula.text.toString().trim()

        if (nombres.isEmpty() || apellidos.isEmpty()) {
            Toast.makeText(this, "Nombres y apellidos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val datos = hashMapOf(
            "nombres" to nombres,
            "apellidos" to apellidos,
            "cedula" to cedula
        )

        db.collection("personas")
            .document(cedula)
            .set(datos)
            .addOnSuccessListener {
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
            }
    }

    private fun listar() {
        lista.clear()
        db.collection("personas")
            .get()
            .addOnSuccessListener { documentos ->
                for (doc in documentos) {

                    val nombres = doc.getString("nombres") ?: ""
                    val apellidos = doc.getString("apellidos") ?: ""
                    val cedula = doc.id
                    val item = "$nombres - $apellidos - $cedula"
                    lista.add(item)
                }
                adapter.notifyDataSetChanged()
            }
    }

    private fun eliminar() {
        if (documentoSeleccionado.isEmpty()) {
            Toast.makeText(this, "Seleccione un registro de la lista", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("personas")
            .document(documentoSeleccionado)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show()
                listar()
            }
    }

    private fun limpiarCampos() {
        edtNombres.setText("")
        edtApellidos.setText("")
        edtCedula.setText("")
    }
}