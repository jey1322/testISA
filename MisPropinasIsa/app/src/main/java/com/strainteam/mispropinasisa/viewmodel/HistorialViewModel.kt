package com.strainteam.mispropinasisa.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.usecases.getHistorialUseCase
import java.text.SimpleDateFormat
import java.util.Locale

class HistorialViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    val historialList = MutableLiveData<HistorialList>()
    var getHistorialUseCase = getHistorialUseCase(context)
    var valorPropina = MutableLiveData<Double>()
    var valorTotal = MutableLiveData<String>()
    var noData = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
    var errorProp = MutableLiveData<Boolean>()

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

    fun validarData(NombreComer: String, subtotal: String, propinaPorcentaje: String, moneda: String, codMoneda: String) {
        if(NombreComer.isEmpty() || subtotal.isEmpty() || propinaPorcentaje.isEmpty() || moneda.isEmpty() || codMoneda.isEmpty()){
            error.postValue(true)
            return
        } else if(propinaPorcentaje.toDouble() > 100){
            errorProp.postValue(true)
            return
        }else{
            val propina =(subtotal.toDouble() * propinaPorcentaje.toDouble())/100
            val total : Double = subtotal.toDouble() + propina
            valorPropina.postValue(propina)
            valorTotal.postValue(total.toString())
        }
    }
    fun enviarRegistro(NombreComer: String, subtotal: String, propinaPorcentaje: String, moneda: String, codMoneda: String){
        val propina =(subtotal.toDouble() * propinaPorcentaje.toDouble())/100
        val total = subtotal + propina
        val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())
        getHistorialUseCase.saveHistorial(NombreComer, subtotal.toDouble(), propinaPorcentaje, propina.toDouble(), total.toDouble(), fecha, moneda, codMoneda)
    }

    fun getNewHistorial(){
        val result = getHistorialUseCase.getNewHistorial()
        historialList.postValue(result)
    }
}