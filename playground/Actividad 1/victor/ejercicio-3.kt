/* 

Ejercicio 3

Kotlin distingue entre tipos que pueden ser nulos y los que no. Esta distinción es
verificada estáticamente. La tarea es implementar una función que reciba una lista
de montos transaccionales (algunos de los cuales pueden ser null). A partir de esta
lista, utilizar el operador de llamada segura (?.) o el operador Elvis (?:) para retornar
la suma total, tratando los valores nulos como 0.0.
Restricción: No usar de sentencias if (x != null). 

*/

fun main() {
    val montos = listOf(100.00, null, 120.00)
    println(sumaTotalSegura(montos))
}


fun sumaTotalSegura(lista: List<Double?>): Double {
    var suma = 0.00
    lista.forEach {
    	suma += it?:0.00
	}
    return suma
} 