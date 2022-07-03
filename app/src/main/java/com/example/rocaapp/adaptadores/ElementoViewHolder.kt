package com.example.rocaapp.adaptadores

import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.DAtos.consultar_datos
import com.example.rocaapp.Layouts.Calas
import com.example.rocaapp.Layouts.Inventario
import com.example.rocaapp.R
import com.example.rocaapp.databinding.ActivityElementoBinding

class ElementoViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ActivityElementoBinding.bind(view)

    fun render(elementoSimpleModel: ElementoSimple) {

        binding.SeleccionarElementos.text = elementoSimpleModel.elementosimple

        binding.SeleccionarElementos.setOnClickListener() {


            consultar_datos.elemento=elementoSimpleModel.elementosimple



            val inventario= Intent(binding.SeleccionarElementos.context,Inventario ::class.java)

            startActivity(binding.SeleccionarElementos.context,inventario,null)





            Toast.makeText(
                binding.SeleccionarElementos.context,
                elementoSimpleModel.elementosimple,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}