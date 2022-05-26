package com.example.rocaapp.DAtos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.R

class Adapter (private  val userlist:ArrayList<Cilindros>):RecyclerView.Adapter<Adapter.MyViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.equipo_usuario,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem=userlist[position]
        holder.usuario.text=currentitem.usuario
        holder.fecha.text=currentitem.fecha

//        val currentitem=userlist[position]
//        holder.firstName.text=currentitem.firstName
//        holder.lastName.text=currentitem.lastName
//        holder.age.text=currentitem.age.toString()


    }

    override fun getItemCount(): Int {
        return userlist.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val usuario: TextView =itemView.findViewById(R.id.tvUsuario)
        val fecha: TextView =itemView.findViewById(R.id.tvFechaCilindros)
//        val id: TextView =itemView.findViewById(R.id.tvidCilindros)

    }
}