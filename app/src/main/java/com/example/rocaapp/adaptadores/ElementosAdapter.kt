package com.example.rocaapp.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.R

class ElementosAdapter(private val ElementoLista:List<ElementoSimple>):RecyclerView.Adapter<ElementoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ElementoViewHolder(layoutInflater.inflate(R.layout.activity_elemento,parent,false))
    }

    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) {
        val item=ElementoLista[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
       return ElementoLista.size
    }
}