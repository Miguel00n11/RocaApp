package com.example.rocaapp.DAtos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.R
import com.example.rocaapp.databinding.EquipoUsuarioBinding

class Adapter (private  val userlist:ArrayList<Cilindros>, private val  onClickListener:(Cilindros)->Unit):RecyclerView.Adapter<Adapter.MyViewHolder> (){

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
        holder.actualizar.text=currentitem.fecha
        holder.render(currentitem,onClickListener)



    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = EquipoUsuarioBinding.bind(itemView)
        val usuario: TextView =itemView.findViewById(R.id.tvUsuario)
        val fecha: TextView =itemView.findViewById(R.id.tvFechaCilindros)
        val actualizar: Button =itemView.findViewById(R.id.bAcutalizarCilindro)

        fun render(superHeroModel: Cilindros, onClickListener: (Cilindros) -> Unit) {
            binding.tvUsuario.text = superHeroModel.usuario
            binding.tvFechaCilindros.text = superHeroModel.fecha

            actualizar.setOnClickListener(){ Toast.makeText(binding.tvUsuario.context, superHeroModel.fecha, Toast.LENGTH_SHORT).show()}

            itemView.setOnClickListener { onClickListener(superHeroModel) }


        }

    }
}