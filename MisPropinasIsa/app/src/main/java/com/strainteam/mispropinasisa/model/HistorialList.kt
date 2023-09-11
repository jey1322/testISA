package com.strainteam.mispropinasisa.model

data class HistorialList(
    val Lista : List<Historial>
){
    class Historial{
        val id: Int = 0
        val nombreComercio: String = ""
        val subtotal : Double = 0.0
        val descuentoPorcentaje : String = ""
        val descuento : Double = 0.0
        val total : Double = 0.0
        val fecha : String = ""
    }
}
