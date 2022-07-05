package com.example.rocaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.rocaapp.DAtos.consultar_datos
import com.example.rocaapp.Layouts.SeleccionarAccion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val registrarse=findViewById<Button>(R.id.bRegistrar)
        val acceder=findViewById<Button>(R.id.acceder)
//        val enviarRegistro1=findViewById<Button>(R.id.bEnviarRegistro1)
        val modoinvitado=findViewById<Button>(R.id.btInvitado)


        val email=findViewById<EditText>( R.id.etEmail)
        val password=findViewById<EditText>(R.id.etPassword)

        auth = Firebase.auth

//        registrarse.setOnClickListener{Registrar()}
//        enviarRegistro1.setOnClickListener{}

    acceder.setOnClickListener{
        acceder1(email.text.toString(),password.text.toString())
    }


        modoinvitado.setOnClickListener {

        consultar_datos.modoInvitado=true
            consultar_datos.usuarioApp="Modo Invitado"
            val Acceder=Intent(this,SeleccionarAccion::class.java)
            startActivity(Acceder) }

    }
    private fun Registrar(){

        val Registrarse=Intent(this,Registrarse::class.java)
//        Registrarse.putExtra(TAG,"K")
//            putExtra("Provider",provider.name)
        startActivity(Registrarse)

    }
    private fun Acceder(){

        val Acceder=Intent(this,SeleccionarAccion::class.java)
        consultar_datos.modoInvitado=false
//        Registrarse.putExtra(TAG,"K")
//            putExtra("Provider",provider.name)
        startActivity(Acceder)

    }
    private fun acceder1(email:String,password:String){
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "createUserWithEmail:success")
                        consultar_datos.usuarioApp=email
                        val user = auth.currentUser
                        Acceder()
//                    updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        showAlert()
//                    updateUI(null)
                    }
                }
        }catch (e:Exception){}



    }

    private fun showAlert(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autentificacion al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()


    }
    private fun showError(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Introduzca los datos correspondientes")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()


    }
}