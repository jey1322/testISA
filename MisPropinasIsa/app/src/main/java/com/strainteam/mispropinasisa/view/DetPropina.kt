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
            binding.tvFecha.text = it.Data.fecha
            binding.tvNombreLocal.text = it.Data.nombreComercio
            binding.tvMonto.text = "Monto: "+it.Data.subtotal.toString()
            binding.tvDescuento.text = "Propina: ${it.Data.propinaPorcentaje}%"
            binding.tvTotalDesc.text = "Monto Prop: ${it.Data.propina}"
            binding.tvTotal.text = "Total: ${it.Data.total}"
            binding.tvMoneda.text = it.Data.moneda+" "+it.Data.idMoneda
        })
    }
}