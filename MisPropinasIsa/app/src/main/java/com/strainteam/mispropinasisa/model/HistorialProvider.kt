package com.strainteam.mispropinasisa.model

import android.content.Context
import android.database.Cursor
import com.strainteam.mispropinasisa.sqliteHelper.DbHelper

class HistorialProvider(context: Context) {

    private val mHistorial : MutableList<HistorialList.Historial> = ArrayList()

    val cursorHist : Cursor = DbHelper(context, null).getHistorial()

}