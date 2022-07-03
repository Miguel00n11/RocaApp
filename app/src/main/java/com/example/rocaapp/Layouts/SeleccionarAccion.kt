package com.example.rocaapp.Layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rocaapp.R
import com.example.rocaapp.Registrarse
import com.example.rocaapp.adaptadores.elemento_recycler

class SeleccionarAccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_accion)


        val seleccionarCalas=findViewById<Button>(R.id.bCalas)
        val inventario=findViewById<Button>(R.id.bInventario)

        seleccionarCalas.setOnClickListener(){
            val selecionarCalas= Intent(this,Calas ::class.java)
//        Registrarse.putExtra(TAG,"K")
//            putExtra("Provider",provider.name)
            startActivity(selecionarCalas)
        }

        inventario.setOnClickListener(){
            val selecionarCalas= Intent(this,elemento_recycler ::class.java)

            startActivity(selecionarCalas)
        }


    }
}