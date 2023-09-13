package com.strainteam.mispropinasisa.repository

import android.content.Context
import android.database.Cursor
import com.strainteam.mispropinasisa.model.HistorialList
import com.strainteam.mispropinasisa.model.HistorialProvider
import com.strainteam.mispropinasisa.sqliteHelper.DbHelper

class HistorialRepository(context: Context) {
    private val db = DbHelper(context = context, null)

    fun getHistorial(): Cursor {
        return db.getHistorial()
    }

    fun getAllHistorial(): HistorialList{
        val cursor = getHistorial()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombreComercio = cursor.getString(1)
                val subtotal = cursor.getDouble(2)
                val descuentoPorcentaje = cursor.getString(3)
                val descuento = cursor.getDouble(4)
                val total = cursor.getDouble(5)
                val fecha = cursor.getString(6)
                val moneda = cursor.getString(7)
                val idMoneda = cursor.getString(8)
                val historial = HistorialList.Historial().apply {
                    this.id = id
                    this.nombreComercio = nombreComercio
                    this.subtotal = subtotal
                    this.descuentoPorcentaje = descuentoPorcentaje
                    this.descuento = descuento
                    this.total = total
                    this.fecha = fecha
                    this.moneda = moneda
                    this.idMoneda = idMoneda
                }
                val response = HistorialProvider.historial.lista.plus(historial)
                HistorialProvider.historial = HistorialList(lista = response)
            } while (cursor.moveToNext())
        }
        return HistorialProvider.historial
    }
}