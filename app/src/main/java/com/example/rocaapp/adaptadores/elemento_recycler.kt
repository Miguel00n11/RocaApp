package com.example.rocaapp.adaptadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rocaapp.Cilindros
import com.example.rocaapp.R
import com.example.rocaapp.databinding.ActivityCalasBinding
import com.example.rocaapp.databinding.ActivityElementoRecyclerBinding

class elemento_recycler : AppCompatActivity() {

    private lateinit var binding: ActivityElementoRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityElementoRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniRecyclerView()



    }
    fun iniRecyclerView(){

//        val recyclerview=findViewById<RecyclerView>(R.id.ElementoRecycle)
        binding.ElementoRecycle
        binding.ElementoRecycle.layoutManager= LinearLayoutManager(this)
        binding.ElementoRecycle.adapter=ElementosAdapter(TodosElementos.ElementoLista)
    }


}