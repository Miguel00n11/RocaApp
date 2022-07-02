package com.example.rocaapp.Layouts

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.DAtos.Adapter
import com.example.rocaapp.DAtos.Personal
import com.example.rocaapp.DAtos.consultar_datos
import com.example.rocaapp.R
import com.example.rocaapp.otros.DatePickerFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.common.collect.Collections2.filter
import com.google.common.collect.Iterables.filter
import com.google.common.collect.Iterators.filter
import com.google.common.collect.Sets.filter
//import com.example.rocaapp.databinding.EquipoUsuarioBinding
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference


import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.Locale.filter
import java.util.Locale.getDefault
import kotlin.collections.ArrayList

private lateinit var dbref: DatabaseReference
private lateinit var dbrefPersonal: DatabaseReference
private lateinit var userRecyclerView: RecyclerView
private lateinit var userArrayList:ArrayList<Cilindros>
private lateinit var newuserArrayList:ArrayList<Cilindros>
private lateinit var txtbuscar:SearchView
//private lateinit var userArrayListPersonal:ArrayList<Personal>

class Inventario : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)



        userRecyclerView=findViewById(R.id.cilindrosLista)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf<Cilindros>()
        newuserArrayList= arrayListOf<Cilindros>()
        getUserData()



        val fab=findViewById<FloatingActionButton>(R.id.fab1)
        fab.setOnClickListener {


            agregarCilindro((userArrayList.size).toString())


        }


    }
    private fun getUserData() {

        dbref= FirebaseDatabase.getInstance().getReference("inventario").child("${consultar_datos.elemento}")
        dbrefPersonal= FirebaseDatabase.getInstance().getReference("personal")



        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snashot: DataSnapshot) {

                if (snashot.exists()){
                    userArrayList.clear()
                    for(userSnapshot in snashot.children){
                        val user=userSnapshot.getValue(Cilindros::class.java)


                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter=Adapter(userArrayList) { cilindros ->


//                            superHero ->
                        onItemSelected(
                            cilindros
                        )
                    }
                }

                val adaptador1:ArrayAdapter<String>

                adaptador1= ArrayAdapter(this@Inventario,android.R.layout.simple_list_item_activated_1)

                txtbuscar=findViewById(R.id.txtBuscar)
                txtbuscar.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(usuario: String): Boolean {
//                        txtbuscar.clearFocus()
                        if (userArrayList.contains(Cilindros(usuario))){
//                            adaptador1.filter.filter(usuario)
                            userArrayList.filter { cilindros: Cilindros ->  cilindros.usuario==usuario }
                        }else{
                            Toast.makeText(applicationContext,"no encontrado",Toast.LENGTH_LONG).show()
                        }
                        return false
                    }

                    override fun onQueryTextChange(usuario: String): Boolean {
                       adaptador1.filter.filter(usuario)
//                        userArrayList.filter { cilindros: Cilindros ->  cilindros.usuario=="Luis" }


                        userArrayList.clear()
                        val searchText=usuario!!.toLowerCase(Locale.getDefault())
                        if (searchText.isNotEmpty()){
                            userArrayList.forEach {
                                if (it.usuario!!.toLowerCase(getDefault()).contains(searchText)){
                                    userArrayList.add(it)
                                }
                            }
                            userRecyclerView.adapter!!.notifyDataSetChanged()
                        }else{
                            userArrayList.clear()
                            userArrayList.addAll(userArrayList)
                            userRecyclerView.adapter!!.notifyDataSetChanged()
                        }


                        return false


                    }

                })


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })



    }
    fun onItemSelected(superHero1: Cilindros){

//        val actualizar: Button = findViewById(R.id.bAcutalizarCilindro)
//        actualizar.setOnClickListener {
//        Toast.makeText(this, "superHero1.usuari44o", Toast.LENGTH_SHORT).show() }



        val builder= AlertDialog.Builder(this)
        val view=layoutInflater.inflate(R.layout.actualizar_cilindro1,null)
//        val botonCil=findViewById<Button>(R.id.botonActualizarCilindros1)
//        val usuarioCilindro=findViewById<EditText>(R.id.etUsuarioCilindro)
//        val fechaCilindros=findViewById<EditText>(R.id.etFechaCilindro)

//        consultar_datos.idCilindroActualizar=superHero1.IdCilindro.toString().toInt()


//        val textoCilin=view.findViewById<EditText>(R.id.etUsuarioCilindro1)
        builder.setView(view)

//            .setNegativeButton(R.string.cancelar,
//                DialogInterface.OnClickListener { dialog, id ->
//                    Toast.makeText(this, "Cancelaste", Toast.LENGTH_SHORT).show()
//                })
//            .setPositiveButton(R.string.aceptar,
//                DialogInterface.OnClickListener { dialog, id ->
//
//                    Toast.makeText(this, "Aceptaste", Toast.LENGTH_SHORT).show()
////                    actualizarCilindros()
//                })



        val dialog=builder.create()

            dialog.show()




        val ActualizarCilindroCancelar=view.findViewById<Button>(R.id.ActualizarCilindrosCancelar)
//        val idcilindro  =view.findViewById<TextView>(R.id.tvIdActualizarCilindro1)
//        view.findViewById<TextView>(R.id.tvIdActualizarCilindro1).text=superHero1.id
//        view.findViewById<EditText>(R.id.etUsuarioActualizarCilindro1).setText(superHero1.usuario)
        val usuario=view.findViewById<EditText>(R.id.etUsuarioActualizarCilindro1)
        val fecha=view.findViewById<EditText>(R.id.etFechaActulizarCilindro1)
        val id1=view.findViewById<TextView>(R.id.tvIdActualizarCilindro1)

        id1.text=superHero1.id
        fecha.setText(superHero1.fecha.toString())
        usuario.setText(superHero1.usuario.toString())


        val calendario=view.findViewById<Button>(R.id.mostrarCalendario)
        calendario.setOnClickListener {


            val datePicker=DatePickerFragment({day,month,year -> onDateSelect(day,month,year)
                fecha.setText(onDateSelect(day,month+1,year))})

            datePicker.show(supportFragmentManager,"datePicker")


        }


        ActualizarCilindroCancelar.setOnClickListener(){

            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
            dialog.hide()
        }
        val ActualizarCilindroAceptar=view.findViewById<Button>(R.id.ActualizarCilindrosAceptar)
        ActualizarCilindroAceptar.setOnClickListener(){



            actualizarCilindros("${id1.text}", fecha = "${fecha.text}", usuario = "${usuario.text}")
//            actualizarCilindros("1", fecha = "1", usuario = "1")
//            consultar_datos.UsuarioCilindroActualziar=UsuarioCilindroActualizar.text.toString()
//            Toast.makeText(this, "${id1.text}", Toast.LENGTH_SHORT).show()
            dialog.hide()
        }





//        Toast.makeText(this, "superHero1.usuario", Toast.LENGTH_SHORT).show()
    }

//    fun actualizarCilindros(id:String,fecha:String,usuario:String){
        fun actualizarCilindros(id:String,fecha:String,usuario:String){
//        val binding = EquipoUsuarioBinding.bind(itemView)

        dbref = FirebaseDatabase.getInstance().getReference("inventario").child("${consultar_datos.elemento}")

        val post=Cilindros(fecha = fecha, usuario = usuario,id=id)
        val childUp= hashMapOf<String,Any>(id to post)

        dbref.updateChildren(childUp)

    }

    fun onDateSelect(day:Int,month:Int,year:Int):String{

//        view.findViewById<EditText>(R.id.etFechaActulizarCilindro1).setText(""+day+  "/"+month+"/"+year)
        val fecha=""+day+  "/"+month+"/"+year
        return fecha
    }

    fun agregarCilindro(idd: String){
        val builder1= AlertDialog.Builder(this)
        val view1=layoutInflater.inflate(R.layout.actualizar_cilindro1,null)
        builder1.setView(view1)
        val dialog1=builder1.create()

        dialog1.show()


        val usuario=view1.findViewById<EditText>(R.id.etUsuarioActualizarCilindro1)
        val fecha=view1.findViewById<EditText>(R.id.etFechaActulizarCilindro1)
        val id1=view1.findViewById<TextView>(R.id.tvIdActualizarCilindro1)

        id1.text=idd

        val calendario1=view1.findViewById<Button>(R.id.mostrarCalendario)
            calendario1.setOnClickListener {

                val datePicker=DatePickerFragment({day,month,year -> onDateSelect(day,month,year)
                    fecha.setText(onDateSelect(day,month+1,year))})

                datePicker.show(supportFragmentManager,"datePicker")


            }

        val ActualizarCilindroAceptar=view1.findViewById<Button>(R.id.ActualizarCilindrosAceptar)
        ActualizarCilindroAceptar.setOnClickListener(){


            actualizarCilindros("${id1.text}", fecha = "${fecha.text}", usuario = "${usuario.text}")
            dialog1.hide()
        }



//
//        val ActualizarCilindroAceptar=view1.findViewById<Button>(R.id.ActualizarCilindrosAceptar)
//        ActualizarCilindroAceptar.setOnClickListener(){
//
//
//            val usuario=view1.findViewById<EditText>(R.id.etUsuarioActualizarCilindro1)
//            val fecha=view1.findViewById<EditText>(R.id.etFechaActulizarCilindro1)
////                val id1=view1.findViewById<TextView>(R.id.tvIdActualizarCilindro1)
//            view1.findViewById<TextView>(R.id.tvIdActualizarCilindro1).setText(idd)
//                fecha.setText(idd)
//
//            val calendario1=view1.findViewById<Button>(R.id.mostrarCalendario)
//            calendario1.setOnClickListener {
//
//                Toast.makeText(this, userArrayList.size.toString(),Toast.LENGTH_LONG).show()
//                fecha.setText(userArrayList.size.toString())
//
//
//            }
//
//            Toast.makeText(this, userArrayList.size.toString(),Toast.LENGTH_LONG).show()
//
//            dialog1.hide()
//        }
    }





}