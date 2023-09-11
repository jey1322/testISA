package com.strainteam.mispropinasisa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.sqliteHelper.DbHelper

class HistorialViewModel: ViewModel() {

    val historialList = MutableLiveData<HistorialList>()

}