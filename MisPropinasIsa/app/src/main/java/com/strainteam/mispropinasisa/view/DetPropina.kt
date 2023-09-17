package com.strainteam.mispropinasisa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.strainteam.mispropinasisa.R
import com.strainteam.mispropinasisa.databinding.ActivityDetPropinaBinding
import com.strainteam.mispropinasisa.databinding.EditPropinaBinding
import com.strainteam.mispropinasisa.databinding.SheetAddPropinaBinding
import com.strainteam.mispropinasisa.viewmodel.HistorialViewModel

class DetPropina : AppCompatActivity() {
    private lateinit var binding : ActivityDetPropinaBinding
    private val historialViewModel: HistorialViewModel by viewModels()
    private var nombre = ""
    private var monto = ""
    private var propinaPorcentaje = ""
    private var propina = 0.0
    private var total = ""
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetPropinaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("id",0).toString()
        historialViewModel.getOneHistorial(id)

        historialViewModel.getOneHist.observe(this, Observer {
            binding.tvNombreLocal.text = it.Data.nombreComercio
            binding.tvFecha.text = it.Data.fecha
            binding.tvMoneda.text = it.Data.moneda+" "+it.Data.idMoneda
            binding.tvMonto.text = "Monto: ${it.Data.subtotal}"
            binding.tvDescuento.text = "Propina: ${it.Data.propinaPorcentaje}%"
            binding.tvTotalDesc.text = "Monto Prop: ${it.Data.propina}"
            binding.tvTotal.text = "Total: ${it.Data.total}"
        })
        historialViewModel.error.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Error debe llenar los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        })
        historialViewModel.errorProp.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Error el porcentaje de propina no puede ser mayor al 100%", Toast.LENGTH_SHORT).show()
            }
        })
        historialViewModel.valorPropina.observe(this, Observer {
            propina = it
        })
        historialViewModel.valorTotal.observe(this, Observer {
            total = it
            confirmar()
        })

        binding.btnEliminar.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            builder.background = getDrawable(R.drawable.recycler)
            builder.setTitle("Desea eliminar este registro?")
            builder.setMessage("Si elimina, no podrá recuperar este dato")
            builder.setPositiveButton("Eliminar"){ _, _ ->
                historialViewModel.deleteHistorial(id)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("Cancelar"){ _, _ -> }
            builder.show()
        }

        binding.btnEditar.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            builder.background = getDrawable(R.drawable.button_cancel)
            val view = layoutInflater.inflate(R.layout.edit_propina, null)
            val binding2 = EditPropinaBinding.bind(view)
            builder.setView(binding2.root)
            val dialog = builder.create()
            dialog.show()
            binding2.etNombreLocal.setText(binding.tvNombreLocal.text.toString())
            binding2.etMonto.setText(binding.tvMonto.text.toString().split(" ")[1])
            binding2.etPropina.setText(binding.tvDescuento.text.toString().split(" ")[1].split("%")[0])
            binding2.btnCancelar.setOnClickListener {
                dialog.dismiss()
            }
            binding2.btnGuardar.setOnClickListener {
                nombre = binding2.etNombreLocal.text.toString()
                monto = binding2.etMonto.text.toString()
                propinaPorcentaje = binding2.etPropina.text.toString()
                historialViewModel.validarUpdate(nombre, monto, propinaPorcentaje)
            }
        }

    }

    private fun confirmar(){
        val builder = MaterialAlertDialogBuilder(this)
        builder.background = getDrawable(R.drawable.recycler)
        builder.setTitle("Confirmar")
        builder.setMessage("¿Desea guardar esta propina?\n" +
                "Monto propina: $propina\n" +
                "Total a pagar: $total\n")
        builder.setPositiveButton("Guardar"){ _, _ ->
            historialViewModel.updateRegistro(id,nombre, monto, propinaPorcentaje)
            historialViewModel.getNewHistorial()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Cancelar"){ _, _ -> }
        builder.show()
    }
}