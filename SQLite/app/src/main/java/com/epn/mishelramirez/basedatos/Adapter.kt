package com.epn.mishelramirez.basedatos


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.epn.mishelramirez.basedatos.R
import com.epn.mishelramirez.basedatos.dao.UsuarioDao
import com.epn.mishelramirez.basedatos.modelo.Usuario

class UsuarioAdapter(
    private val context: Context,
    private val lista: MutableList<Usuario>,
    private val usuarioDao: UsuarioDao
) : BaseAdapter() {

    override fun getCount(): Int = lista.size

    override fun getItem(position: Int): Any = lista[position]

    override fun getItemId(position: Int): Long = lista[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_usuario, parent, false)

        val txtDatos = view.findViewById<TextView>(R.id.txtDatos)
        val btnEliminar = view.findViewById<Button>(R.id.btnEliminar)

        val usuario = lista[position]

        txtDatos.text = "${usuario.nombres} ${usuario.apellidos}\nCédula: ${usuario.cedula}"

        btnEliminar.setOnClickListener {
            usuarioDao.eliminar(usuario.id)
            lista.removeAt(position)
            notifyDataSetChanged()
            Toast.makeText(context, "Registro eliminado", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
