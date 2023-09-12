package com.strainteam.mispropinasisa.model

import android.content.Context
import android.database.Cursor
import com.strainteam.mispropinasisa.sqliteHelper.DbHelper

class HistorialProvider(context: Context) {
    companion object{
        var historial : HistorialList = HistorialList(lista = emptyList())
    }
}