package com.epn.mishelramirez.basedatos.modelo

data class Usuario(
    val id: Int = 0,
    var nombres: String,
    var apellidos: String,
    var cedula: String
)
