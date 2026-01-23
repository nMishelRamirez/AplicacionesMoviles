package com.epn.mishelramirez.consumoapirest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.epn.mishelramirez.consumoapirest.implementacion.ApiClientes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //lifecycleScope.launch(Dispatchers.IO) Esta uso el profe
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val clientes= ApiClientes.api.getClientes()
                withContext(Dispatchers.Main){
                    clientes.forEach {
                        Log.d("API", it.Id.toString())
                        Log.d("API -- Nombre", it.Nombre.toString())
                    }
                }
            }catch (e: Exception){
                Log.e("Error",e.message!!);
            }

        }
    }
}