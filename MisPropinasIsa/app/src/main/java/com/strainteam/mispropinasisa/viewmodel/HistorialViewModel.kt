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

    fun onCreate(){
        val result = getHistorialUseCase()
        historialList.postValue(result)
    }
}