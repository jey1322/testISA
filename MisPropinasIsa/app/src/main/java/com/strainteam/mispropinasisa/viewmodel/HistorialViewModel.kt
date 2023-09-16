package com.strainteam.mispropinasisa.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.usecases.getHistorialUseCase

class HistorialViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    val historialList = MutableLiveData<HistorialList>()
    var getHistorialUseCase = getHistorialUseCase(context)
    var noData = MutableLiveData<Boolean>()

    fun onCreate(){
        val result = getHistorialUseCase()
        if(result.lista.isEmpty()) {
            noData.postValue(true)
            historialList.postValue(result)
        }else{
            noData.postValue(false)
            historialList.postValue(result)
        }
    }

    fun saveHistorial(NombreComer: String, subtotal: Double, propinaPorcentaje: String, propina: Double, total: Double, fecha: String, moneda: String, codMoneda: String) {
        getHistorialUseCase.saveHistorial(NombreComer, subtotal, propinaPorcentaje, propina, total, fecha, moneda, codMoneda)
    }

    fun getNewHistorial(){
        val result = getHistorialUseCase.getNewHistorial()
        historialList.postValue(result)
    }
}