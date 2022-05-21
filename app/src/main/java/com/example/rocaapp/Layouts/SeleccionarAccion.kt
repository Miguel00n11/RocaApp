package com.example.rocaapp.Layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rocaapp.R
import com.example.rocaapp.Registrarse

class SeleccionarAccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_accion)


        val seleccionarCalas=findViewById<Button>(R.id.bCalas)

        seleccionarCalas.setOnClickListener(){
            val selecionarCalas= Intent(this,Calas ::class.java)
//        Registrarse.putExtra(TAG,"K")
//            putExtra("Provider",provider.name)
            startActivity(selecionarCalas)
        }



    }
}