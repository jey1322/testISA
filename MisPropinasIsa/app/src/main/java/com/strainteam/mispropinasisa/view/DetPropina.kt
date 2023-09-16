package com.strainteam.mispropinasisa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.strainteam.mispropinasisa.R
import com.strainteam.mispropinasisa.databinding.ActivityDetPropinaBinding
import com.strainteam.mispropinasisa.viewmodel.HistorialViewModel

class DetPropina : AppCompatActivity() {
    private lateinit var binding : ActivityDetPropinaBinding
    private val historialViewModel: HistorialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetPropinaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("id").toString()
        historialViewModel.getOneHistorial(id)

        historialViewModel.getOneHist.observe(this, Observer {
            binding.tvNombreLocal.text = it.Data.nombreComercio
            binding.tvMoneda.text = it.Data.moneda+" "+it.Data.idMoneda
        })
    }
}