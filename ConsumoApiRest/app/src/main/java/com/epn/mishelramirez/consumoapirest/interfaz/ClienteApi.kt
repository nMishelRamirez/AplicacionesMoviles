package com.epn.mishelramirez.consumoapirest.interfaz

import com.epn.mishelramirez.consumoapirest.modelo.Cliente
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ClienteApi {
    @GET("/api/Clientes")
    suspend fun getClientes(): List<Cliente>

    @POST("/api/Clientes")
    suspend fun createCliente(@Body cliente: Cliente)

}