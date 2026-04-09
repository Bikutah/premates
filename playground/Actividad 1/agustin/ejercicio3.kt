fun main() {
	val transacciones = listOf(10.00,20.00,10.00,5.00,null)
    
    println(suma(transacciones))
}


fun suma(transacciones: List<Double?>):Double{
    var total = 0.0
    for(transaccion in transacciones){
        total += transaccion?:0.0
    }
    return total
}