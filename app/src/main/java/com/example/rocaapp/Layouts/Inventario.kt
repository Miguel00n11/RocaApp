package com.example.rocaapp.Layouts

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.DAtos.Adapter
import com.example.rocaapp.DAtos.consultar_datos
import com.example.rocaapp.R
import com.example.rocaapp.databinding.EquipoUsuarioBinding
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var dbref: DatabaseReference
private lateinit var userRecyclerView: RecyclerView
private lateinit var userArrayList:ArrayList<Cilindros>

class Inventario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)


        userRecyclerView=findViewById(R.id.cilindrosLista)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf<Cilindros>()
        getUserData()
//
//        val actualizar=findViewById<Button>(R.id.bAcutalizarCilindro)
//        actualizar.setOnClickListener(){
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
//        }
        
//        val cargar=findViewById<Button>(R.id.cargar)
//        cargar.setOnClickListener{
//            val database = Firebase.database
//            val myRef = database.getReference("prueba").child("id")
//
//            myRef.removeValue()
//mi
//        }

    }
    private fun getUserData() {

        dbref= FirebaseDatabase.getInstance().getReference("inventario").child("Cilindros")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snashot: DataSnapshot) {

                if (snashot.exists()){
                    for(userSnapshot in snashot.children){
                        val user=userSnapshot.getValue(Cilindros::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter=Adapter(userArrayList) { superHero ->
                        onItemSelected(
                            superHero
                        )
                    }
                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
    fun onItemSelected(superHero1: Cilindros){

        val builder= AlertDialog.Builder(this)
        val view=layoutInflater.inflate(R.layout.actualizar_cilindro1,null)
//        val botonCil=findViewById<Button>(R.id.botonActualizarCilindros1)
//        val usuarioCilindro=findViewById<EditText>(R.id.etUsuarioCilindro)
//        val fechaCilindros=findViewById<EditText>(R.id.etFechaCilindro)

//        consultar_datos.idCilindroActualizar=superHero1.IdCilindro.toString().toInt()


        val textoCilin=view.findViewById<EditText>(R.id.etUsuarioCilindro1)
        builder.setView(view)

            .setNegativeButton(R.string.cancelar,
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this, "Cancelaste", Toast.LENGTH_SHORT).show()
                })
            .setPositiveButton(R.string.aceptar,
                DialogInterface.OnClickListener { dialog, id ->

                    Toast.makeText(this, "Aceptaste", Toast.LENGTH_SHORT).show()
//                    actualizarCilindros()
                })



        val dialog=builder.create()

            dialog.show()

        val ActualizarCilindroCancelar=view.findViewById<Button>(R.id.ActualizarCilindrosCancelar)
        val UsuarioCilindroActualizar=view.findViewById<EditText>(R.id.etUsuarioCilindro1)


        ActualizarCilindroCancelar.setOnClickListener(){

            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
            dialog.hide()
        }
        val ActualizarCilindroAceptar=view.findViewById<Button>(R.id.ActualizarCilindrosAceptar)
        ActualizarCilindroAceptar.setOnClickListener(){
            actualizarCilindros(superHero1)
            consultar_datos.UsuarioCilindroActualziar=UsuarioCilindroActualizar.text.toString()
            Toast.makeText(this, "Aceptar", Toast.LENGTH_SHORT).show()
            dialog.hide()
        }



//        Toast.makeText(this, "superHero1.usuario", Toast.LENGTH_SHORT).show()
    }

    fun actualizarCilindros(idCilindros: Cilindros){
//        val binding = EquipoUsuarioBinding.bind(itemView)

        dbref = FirebaseDatabase.getInstance().getReference("inventario").child("Cilindros")

        val post=Cilindros(fecha = "fe123/123", usuario = consultar_datos.UsuarioCilindroActualziar)
        val childUp= hashMapOf<String,Any>("${idCilindros.IdCilindro}" to post)

        dbref.updateChildren(childUp)

    }

}