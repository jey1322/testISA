package com.strainteam.mispropinasisa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.strainteam.mispropinasisa.R
import com.strainteam.mispropinasisa.adapter.HistorialAdapter
import com.strainteam.mispropinasisa.databinding.ActivityMainBinding
import com.strainteam.mispropinasisa.databinding.SheetAddPropinaBinding
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.viewmodel.HistorialViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val historialViewModel: HistorialViewModel by viewModels()
    private var mHistorial : MutableList<HistorialList.Historial> = ArrayList()
    private lateinit var adapter : HistorialAdapter
    private var propina = 0.0
    private var total = ""
    private var moneda = ""
    private var codMoneda = ""
    private var nombre = ""
    private var monto = ""
    private var propinaPorcentaje = ""
    private lateinit var dialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        historialViewModel.onCreate()

        historialViewModel.historialList.observe(this, Observer {
            mHistorial.clear()
            mHistorial.addAll(it.lista)
            adapter.notifyDataSetChanged()
        })
        historialViewModel.noData.observe(this, Observer {
            if(it){
                binding.ivAdd.visibility = View.VISIBLE
                binding.tvAdd.visibility = View.VISIBLE
            }else{
                binding.ivAdd.visibility = View.GONE
                binding.tvAdd.visibility = View.GONE
            }
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

        binding.tvNew.setOnClickListener {
            mostrarSheet()
        }
        binding.ivAdd.setOnClickListener {
            mostrarSheet()
        }
        binding.tvAdd.setOnClickListener {
            mostrarSheet()
        }
    }

    private fun mostrarSheet(){
        dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.sheet_add_propina, null)
        val binding2 = SheetAddPropinaBinding.bind(view)
        dialog.setContentView(binding2.root)
        dialog.show()
        binding2.btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
        val monedasList = ArrayList<String>()
        monedasList.add("Cordobas - NIO")
        monedasList.add("Dolares - USD")
        binding2.spMoneda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, monedasList)
        binding2.spMoneda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Seleccione una moneda", Toast.LENGTH_SHORT).show()
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val monedaSplit = monedasList[position].split(" - ")
                moneda = monedaSplit[0]
                codMoneda = monedaSplit[1]
            }
        }
        binding2.btnGuardar.setOnClickListener {
            nombre = binding2.etNombreLocal.text.toString()
            monto = binding2.etMonto.text.toString()
            propinaPorcentaje = binding2.etPropina.text.toString()
            historialViewModel.validarData(nombre, monto, propinaPorcentaje, moneda, codMoneda)
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
            historialViewModel.enviarRegistro(nombre, monto, propinaPorcentaje, moneda, codMoneda)
            historialViewModel.getNewHistorial()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar"){ _, _ -> }
        builder.show()
    }

    private fun initRecycler(){
        binding.rvPropinas.layoutManager = LinearLayoutManager(this)
        adapter = mHistorial.let { HistorialAdapter(this, it, R.layout.propinas_list) }
        binding.rvPropinas.adapter = adapter
    }
}