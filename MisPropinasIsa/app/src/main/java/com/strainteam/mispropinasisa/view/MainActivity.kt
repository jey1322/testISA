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
        binding.ivAdd.setOnClickListener {
            mostrarSheet()
        }
        binding.tvAdd.setOnClickListener {
            mostrarSheet()
        }
    }

    private fun mostrarSheet(){
        val dialog = BottomSheetDialog(this)
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
        monedasList.add("Euros - EUR")
        var moneda = ""
        var codMoneda = ""
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
            if(binding2.etNombreLocal.text.toString().isEmpty() || binding2.etMonto.text.toString().isEmpty() || binding2.etPropina.text.toString().isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }else if(binding2.etPropina.text.toString().toDouble() > 100) {
                binding2.etPropina.error = "El porcentaje de propina no puede ser mayor al 100%"
                binding2.etPropina.requestFocus()
            }else{
                val subtotal = binding2.etMonto.text.toString().toDouble()
                val propinaPorcentaje = binding2.etPropina.text.toString()
                val propina = (subtotal * propinaPorcentaje.toDouble()) / 100
                val total = subtotal + propina
                val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                val builder = MaterialAlertDialogBuilder(this)
                builder.background = getDrawable(R.drawable.recycler)
                builder.setTitle("Confirmar")
                builder.setMessage("¿Desea guardar esta propina?\n" +
                        "Nombre del local: ${binding2.etNombreLocal.text}\n" +
                        "Subtotal: $subtotal\n" +
                        "Propina: $propinaPorcentaje%\n" +
                        "Monto propina: $propina\n" +
                        "Total: $total\n" +
                        "Moneda: $moneda $codMoneda")
                builder.setPositiveButton("Guardar"){ _, _ ->
                    historialViewModel.saveHistorial(binding2.etNombreLocal.text.toString(), subtotal, propinaPorcentaje, propina, total, fecha.format(System.currentTimeMillis()), moneda, codMoneda)
                    dialog.dismiss()
                    historialViewModel.getNewHistorial()
                }
                builder.setNegativeButton("Cancelar"){ _, _ -> }
                builder.show()
            }
        }

    }

    private fun initRecycler(){
        binding.rvPropinas.layoutManager = LinearLayoutManager(this)
        adapter = mHistorial.let { HistorialAdapter(this, it, R.layout.propinas_list) }
        binding.rvPropinas.adapter = adapter
    }
}