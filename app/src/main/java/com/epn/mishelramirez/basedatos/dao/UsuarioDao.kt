package com.epn.mishelramirez.basedatos.dao

import android.content.ContentValues
import android.content.Context
import com.epn.mishelramirez.basedatos.db.DbHelper
import com.epn.mishelramirez.basedatos.modelo.Usuario

class UsuarioDao(context: Context) {

    private val dbHelper = DbHelper(context)

    // CREATE
    fun insertar(usuario: Usuario): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombres", usuario.nombres)
            put("apellidos", usuario.apellidos)
            put("cedula", usuario.cedula)
        }
        return db.insert("usuario", null, values)
    }

    // READ
    fun listar(): List<Usuario> {
        val lista = mutableListOf<Usuario>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuario", null)

        while (cursor.moveToNext()) {
            lista.add(
                Usuario(
                    id = cursor.getInt(0),
                    nombres = cursor.getString(1),
                    apellidos = cursor.getString(2),
                    cedula = cursor.getString(3)
                )
            )
        }
        cursor.close()
        return lista
    }

    // UPDATE
    fun actualizar(usuario: Usuario): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombres", usuario.nombres)
            put("apellidos", usuario.apellidos)
            put("cedula", usuario.cedula)
        }
        return db.update(
            "usuario",
            values,
            "id = ?",
            arrayOf(usuario.id.toString())
        )
    }

    // DELETE
    fun eliminar(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("usuario", "id = ?", arrayOf(id.toString()))
    }
}
