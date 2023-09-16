package com.strainteam.mispropinasisa.sqliteHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ISA.db"
        val TABLE_NAME = "Historial"
        val ID = "id"
        val NOMBRE_COMERCIO = "nombreComercio"
        val SUBTOTAL = "subtotal"
        val PROPINA_PORCENTAJE = "propinaPorcentaje"
        val PROPINA = "propina"
        val TOTAL = "total"
        val FECHA = "fecha"
        val MONEDA = "moneda"
        val IDMONEDA = "idMoneda"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOMBRE_COMERCIO + " TEXT,"
                + SUBTOTAL + " REAL,"
                + PROPINA_PORCENTAJE + " TEXT,"
                + PROPINA + " REAL,"
                + TOTAL + " REAL,"
                + FECHA + " TEXT,"
                + MONEDA + " TEXT,"
                + IDMONEDA + " TEXT" + ");")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun saveHistorial(nombreComercio: String, subtotal: Double, descuentoPorcentaje: String, descuento: Double, total: Double, fecha: String, moneda: String, idMoneda: String){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE_COMERCIO, nombreComercio)
        values.put(SUBTOTAL, subtotal)
        values.put(PROPINA_PORCENTAJE, descuentoPorcentaje)
        values.put(PROPINA, descuento)
        values.put(TOTAL, total)
        values.put(FECHA, fecha)
        values.put(MONEDA, moneda)
        values.put(IDMONEDA, idMoneda)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getHistorial(): Cursor {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        return cursor
    }

    fun getOneHistorial(Id: String): Cursor {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $ID = $Id"
        return db.rawQuery(query, null)
    }

    fun updateOneHistorial (Id: String, nombreComercio: String, subtotal: Double, descuentoPorcentaje: String, descuento: Double, total: Double, fecha: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE_COMERCIO, nombreComercio)
        values.put(SUBTOTAL, subtotal)
        values.put(PROPINA_PORCENTAJE, descuentoPorcentaje)
        values.put(PROPINA, descuento)
        values.put(TOTAL, total)
        values.put(FECHA, fecha)
        db.update(TABLE_NAME, values, "$ID = ?", arrayOf(Id))
        db.close()
    }

    fun deleteOneHistorial (Id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID = ?", arrayOf(Id))
        db.close()
    }

}