package com.epn.mishelramirez.listview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.util.Log


class StringAdapter(
    context: Context,
    private val items: List<String>
) : ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.personali, parent, false)

        val txtTexto = view.findViewById<TextView>(R.id.txtTexto)

        txtTexto.text = items[position]
        
        Log.d("ADAPTER", "Inflando personali.xml en posición $position con valor: ${items[position]}")

        return view
    }
}

