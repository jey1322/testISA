package com.strainteam.mispropinasisa.usecases

import android.content.Context
import com.strainteam.mispropinasisa.repository.HistorialRepository

class getHistorialUseCase (context: Context){
    private val repository = HistorialRepository(context)

    operator fun invoke() = repository.getAllHistorial()
    fun saveHistorial(nombreComer: String, subtotal: Double, propinaPorcentaje: String, propina: Double, total: Double, fecha: String, moneda: String, codMoneda: String) {
        repository.saveHistorial(nombreComer, subtotal, propinaPorcentaje, propina, total, fecha, moneda, codMoneda)
    }
    fun getNewHistorial() = repository.getAllHistorial()
}