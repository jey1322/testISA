package com.strainteam.mispropinasisa.usecases

import android.content.Context
import com.strainteam.mispropinasisa.repository.HistorialRepository

class getHistorialUseCase (context: Context){
    private val repository = HistorialRepository(context)

    operator fun invoke() = repository.getAllHistorial()
}