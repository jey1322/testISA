package com.strainteam.mispropinasisa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.strainteam.mispropinasisa.R
import com.strainteam.mispropinasisa.adapter.HistorialAdapter
import com.strainteam.mispropinasisa.databinding.ActivityMainBinding
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.viewmodel.HistorialViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val historialViewModel: HistorialViewModel by viewModels()
    private var mHistorial : MutableList<HistorialList.Historial> = ArrayList()
    private lateinit var adapter : HistorialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        historialViewModel.onCreate()

        historialViewModel.historialList.observe(this, Observer {
            if(it.lista.isEmpty()){
                binding.ivAdd.visibility = View.VISIBLE
                binding.tvAdd.visibility = View.VISIBLE
            }else{
                binding.ivAdd.visibility = View.GONE
                binding.tvAdd.visibility = View.GONE
                mHistorial.clear()
                mHistorial.addAll(it.lista)
                adapter.notifyDataSetChanged()
            }
        })

        binding.tvNew.setOnClickListener {
            mostrarSheet()
        }
    }

    private fun mostrarSheet(){

    }

    private fun initRecycler(){
        binding.rvPropinas.layoutManager = LinearLayoutManager(this)
        adapter = mHistorial.let { HistorialAdapter(this, it, R.layout.propinas_list) }
        binding.rvPropinas.adapter = adapter
    }
}