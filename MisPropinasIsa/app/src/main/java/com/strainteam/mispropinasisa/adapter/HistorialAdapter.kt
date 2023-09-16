package com.strainteam.mispropinasisa.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strainteam.mispropinasisa.databinding.PropinasListBinding
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.view.DetPropina

class HistorialAdapter(private val context: Context, private val mHistorial: List<HistorialList.Historial>, private val mRowLayout: Int):
RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val binding = PropinasListBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistorialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val historial = mHistorial[position]
        holder.bind(historial)
    }

    override fun getItemCount(): Int {
        return mHistorial.size
    }

    inner class HistorialViewHolder(val binding: PropinasListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(historial: HistorialList.Historial){
            binding.tvFecha.text = historial.fecha
            binding.tvComercio.text = historial.nombreComercio
            binding.tvMonto.text = "Monto: ${historial.subtotal}"
            binding.tvDescuento.text = "Propina: ${historial.propinaPorcentaje}%"
            binding.tvTotalDesc.text = "Monto Prop: ${historial.propina}"
            binding.tvTotal.text = "Total: ${historial.total}"
            binding.tvMoneda.text = historial.moneda+" "+historial.idMoneda

            binding.root.setOnClickListener {
                val intent = Intent(context, DetPropina::class.java)
                intent.putExtra("id", historial.id)
                context.startActivity(intent)
            }
        }
    }
}