package com.example.rocaapp.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.rocaapp.R

class Calas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calas)

        val peso=findViewById<EditText>(R.id.etPesomaterialCala)
        val volumen=findViewById<EditText>(R.id.etVolumenCala)
        val humedad=findViewById<EditText>(R.id.etHuemdadCala)

        val resultado=findViewById<TextView>(R.id.tvResultadoCala)

        val calcular=findViewById<Button>(R.id.bCalcularCala)
        val regresar=findViewById<Button>(R.id.bRegresarCalas)

        calcular.setOnClickListener(){
            resultado.text= CalcularCala(
                peso=peso.text.toString().toDouble(),
                humedad = humedad.text.toString().toDouble(),
                volumen = volumen.text.toString().toDouble()).toString()
        }

        regresar.setOnClickListener(){onBackPressed()}




    }
    private fun CalcularCala( peso:Double, humedad:Double, volumen: Double): Double {

        val AA=peso * volumen * humedad
        return AA
    }
}