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

        val mvsm=findViewById<EditText>(R.id.etMVSM)
        val mvarena=findViewById<EditText>(R.id.etMVarena)
        val peso=findViewById<EditText>(R.id.etPesomaterialCala)
        val Warena=findViewById<EditText>(R.id.etWarena)
        val humedad=findViewById<EditText>(R.id.etHuemdadCala)

        val resultado=findViewById<TextView>(R.id.tvResultadoCala)
        val resultadoCompactacion=findViewById<TextView>(R.id.tvResultadoCompactacion)

        val calcular=findViewById<Button>(R.id.bCalcularCala)
//        val regresar=findViewById<Button>(R.id.bRegresarCalas)

        calcular.setOnClickListener(){
            
            try {
                resultado.text= "%.2f".format( CalcularCala(
                    peso=peso.text.toString().toDouble(),
                    humedad = humedad.text.toString().toDouble(),
                    Warena = Warena.text.toString().toDouble(),
                    MVarena = mvarena.text.toString().toDouble())).toString()
                resultadoCompactacion.text= (("%.2f".format( resultado.text.toString().toDouble()/mvsm.text.toString().toDouble()*100)) + " %").toString()

            }catch (e:Exception){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            } 
            

        }
        
//        regresar.setOnClickListener(){onBackPressed()}




    }
    private fun CalcularCala( peso:Double, humedad:Double, Warena: Double,MVarena:Double): Double {

        val VolArena=Warena/MVarena
        val mvsMuestra=peso/VolArena/(1+humedad/100)

//        val mvsMuestra=mvMuestra(1+humedad/100)
        return mvsMuestra
    }
}