package com.strainteam.mispropinasisa.usecases

import android.content.Context
import com.strainteam.mispropinasisa.model.HistorialDetalle
import com.strainteam.mispropinasisa.repository.HistorialRepository

class getHistorialUseCase (context: Context){
    private val repository = HistorialRepository(context)

    operator fun invoke() = repository.getAllHistorial()

    fun saveHistorial(nombreComer: String, subtotal: Double, propinaPorcentaje: String, propina: Double, total: Double, fecha: String, moneda: String, codMoneda: String) {
        repository.saveHistorial(nombreComer, subtotal, propinaPorcentaje, propina, total, fecha, moneda, codMoneda)
    }

    fun getNewHistorial() = repository.getAllHistorial()

    fun getOneHistorial(Id: String): HistorialDetalle = repository.getOneHistorial(Id)

    fun deleteHistorial(id: String) {
        repository.deleteHistorial(id)
    }

    fun updateRegistro(Id: String, nombreComercio: String, subtotal: Double, descuentoPorcentaje: String, descuento: Double, total: Double, fecha: String){
        repository.updateRegistro(Id,nombreComercio, subtotal, descuentoPorcentaje, descuento, total, fecha)
    }
}