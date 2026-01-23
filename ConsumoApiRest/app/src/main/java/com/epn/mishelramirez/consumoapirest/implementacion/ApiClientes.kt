package com.epn.mishelramirez.consumoapirest.implementacion

import com.epn.mishelramirez.consumoapirest.interfaz.ClienteApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientes {
    private const val URL="http://localhost:5086/" //CAMBIAR
    val api: ClienteApi by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClienteApi::class.java)
    }
}