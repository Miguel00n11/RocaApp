package com.example.rocaapp.DAtos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.R
import com.example.rocaapp.databinding.EquipoUsuarioBinding

class CilindrosHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = EquipoUsuarioBinding.bind(view)

    val usuario = view.findViewById<TextView>(R.id.tvUsuario)
    val fecha = view.findViewById<TextView>(R.id.tvFechaCilindros)


    fun render(superHeroModel: Cilindros, onClickListener: (Cilindros) -> Unit) {
        binding.tvUsuario.text = superHeroModel.usuario
        binding.tvFechaCilindros.text = superHeroModel.fecha


//        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)
//        binding.ivSuperHero.setOnClickListener{
//            Toast.makeText(binding.ivSuperHero.context,
//                superHeroModel.realnameval,
//                Toast.LENGTH_SHORT
//            ).show()
//
//
//        }
        itemView.setOnClickListener { onClickListener(superHeroModel) }

//        itemView.setOnClickListener{  Toast.makeText(binding.ivSuperHero.context,
//            superHeroModel.SuperHero,
//            Toast.LENGTH_SHORT
//        ).show()}
////
//
//        superHero.text=superHeroModel.SuperHero
//        Realname.text=superHeroModel.realnameval
//        publisher.text=superHeroModel.Publisher
//
//        Glide.with(photo.context).load(superHeroModel.photo).into(photo)

    }
}
