package com.example.rocaapp.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.DAtos.Adapter
import com.example.rocaapp.R
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
        Toast.makeText(this, superHero1.usuario, Toast.LENGTH_SHORT).show()
    }

}