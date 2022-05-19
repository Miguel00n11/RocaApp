package com.example.rocaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val guadar=findViewById<Button>(R.id.bGuardar)
//        val recuperar=findViewById<Button>(R.id.bRecuperar)
//        val eliminar=findViewById<Button>(R.id.bEliminar)
//
//        val direccion=findViewById<EditText>(R.id.tvDireccion)
//        val email=findViewById<EditText>(R.id.tvEmail)
//        val phone=findViewById<EditText>(R.id.tvPhone)
//


//        guadar.setOnClickListener(){
//
//            db.collection("users").document(email.text.toString()).
//            set(hashMapOf("provider" to direccion.text.toString(),"phone" to phone.text.toString()))
//        }
//        recuperar.setOnClickListener(){
//
//        }
//        eliminar.setOnClickListener(){
//
//        }

    }

}