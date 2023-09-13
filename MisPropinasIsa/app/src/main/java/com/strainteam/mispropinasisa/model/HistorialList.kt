package com.strainteam.mispropinasisa.model

data class HistorialList(
    var lista : List<Historial>
){
    class Historial{
        var id: Int = 0
        var nombreComercio: String = ""
        var subtotal : Double = 0.0
        var descuentoPorcentaje : String = ""
        var descuento : Double = 0.0
        var total : Double = 0.0
        var fecha : String = ""
        var moneda : String = ""
        var idMoneda : String = ""
    }
}
