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
import java.text.Format
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Locale.filter
import java.util.Locale.getDefault
import kotlin.collections.ArrayList

private lateinit var dbref: DatabaseReference
private lateinit var dbrefPersonal: DatabaseReference
private lateinit var userRecyclerView: RecyclerView
private lateinit var userArrayList:MutableList<Cilindros>
private lateinit var newuserArrayList:ArrayList<Cilindros>
private lateinit var txtbuscar:SearchView
//private lateinit var userArrayListPersonal:ArrayList<Personal>

class Inventario : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        val search=findViewById<SearchView>(R.id.searchView)
//        val listView=findViewById<RecyclerView>(R.id.cilindrosLista)
////        val search=findViewById<SearchView>(R.id.searchView)
//        val adapterfiltro:ArrayAdapter<Cilindros> = ArrayAdapter(this,android.R.layout.simple_list_item_1,
//            userArrayList
//        )
//
//        listView.adapter=adapterfiltro

        userRecyclerView=findViewById(R.id.cilindrosLista)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= mutableListOf<Cilindros>()
//        newuserArrayList= arrayListOf<Cilindros>()
        getUserData()



        val fab=findViewById<FloatingActionButton>(R.id.fab1)
        fab.setOnClickListener {


            if (consultar_datos.usuarioApp=="rooj_civ@hotmail.com"||
                consultar_datos.usuarioApp=="ivan@rocalaboratorio.com"||
                consultar_datos.usuarioApp=="cris.rdz10@gmail.com"){

                agregarCilindro((userArrayList.size).toString())
            }else{}


        }

        val txtelemento=findViewById<TextView>(R.id.txtElemento)
        txtelemento.text=consultar_datos.elemento



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

                    userRecyclerView.adapter=Adapter(userArrayList) {
                            cilindros -> onItemSelected(cilindros)
                    }
                }



//                val adaptador1:ArrayAdapter<String>
//
//                adaptador1= ArrayAdapter(this@Inventario,android.R.layout.simple_list_item_activated_1)

                txtbuscar=findViewById(R.id.searchView)
                txtbuscar.setOnQueryTextListener(object :SearchView.OnQueryTextListener{


                    override fun onQueryTextSubmit(usuario: String): Boolean {

                        return false
                    }

                    override fun onQueryTextChange(usuario: String): Boolean {

                        val searchText=usuario!!.toLowerCase(Locale.getDefault())
                        if (searchText.isNotEmpty()) {
//                            val filtrogg: MutableList<Cilindros> = userArrayList
                            val ususariogg: MutableList<Cilindros> =
//                                userArrayList.filter { it.usuario == usuario } as MutableList<Cilindros>
                                userArrayList.filter { it.usuario?.contains(usuario) == true||it.id?.contains(usuario) == true } as MutableList<Cilindros>



                            userRecyclerView.adapter = Adapter(ususariogg) {
                                    cilindros -> onItemSelected(cilindros)
                            }
                    }
                    else{
                            userRecyclerView.adapter = Adapter(userArrayList) {
                                    cilindros -> onItemSelected(cilindros)
                            }
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

        if (consultar_datos.usuarioApp=="rooj_civ@hotmail.com"||
            consultar_datos.usuarioApp=="ivan@rocalaboratorio.com"||
            consultar_datos.usuarioApp=="cris.rdz10@gmail.com"){

            val builder= AlertDialog.Builder(this)
            val view=layoutInflater.inflate(R.layout.actualizar_cilindro1,null)

            builder.setView(view)


            val dialog=builder.create()

            dialog.show()


            val ActualizarCilindroCancelar=view.findViewById<Button>(R.id.ActualizarCilindrosCancelar)
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



                actualizarCilindros("${id1.text}",
                    fecha = "${fecha.text}",
                    usuario = "${usuario.text}",
                    usuarioApp = "${consultar_datos.usuarioApp}",
                    actualizacionItem="${ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))}"
                )
                dialog.hide()
            }


        }else{}


    }

//    fun actualizarCilindros(id:String,fecha:String,usuario:String){
        fun actualizarCilindros(id:String,fecha:String,usuario:String,usuarioApp:String,actualizacionItem:String){
//        val binding = EquipoUsuarioBinding.bind(itemView)

        dbref = FirebaseDatabase.getInstance().getReference("inventario").child("${consultar_datos.elemento}")

        val post=Cilindros(fecha = fecha,
            usuario = usuario,
            id=id,
            usuarioApp = usuarioApp,
            actualizacionItem=actualizacionItem)
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


            actualizarCilindros("${id1.text}",
                fecha = "${fecha.text}",
                usuario = "${usuario.text}",
                usuarioApp = "${consultar_datos.usuarioApp}",
                actualizacionItem = "${ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))}"
            )
            dialog1.hide()
        }
        val ActualizarCilindroCancelar=view1.findViewById<Button>(R.id.ActualizarCilindrosCancelar)
        ActualizarCilindroCancelar.setOnClickListener(){
            dialog1.hide()
        }

    }





}