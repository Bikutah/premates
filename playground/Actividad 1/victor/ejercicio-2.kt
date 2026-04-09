/**
 * Ejercicio 2
 */

data class UserAccount(val uuid: String, val email: String, val balance: Double)

fun TituloBonito(titulo: String) {
    println("----- $titulo -----")
}

fun main() {
    
    val userAccount = UserAccount("1", "email@gmail.com", 1000.00)
    
    val userAccountCopy = userAccount.copy()
   	
   	// Getters
    TituloBonito("Getters")
    val uuid = userAccount.uuid
   	println(uuid)
    val email = userAccount.email
    println(email)
    val balance = userAccount.balance
    println(balance)
    
   	// ToString
   	TituloBonito("ToString")
    println(userAccount)
   	
   	// Equals
   	TituloBonito("Equals")
    println(userAccount.equals(userAccountCopy))
    
   	// HashCode
   	TituloBonito("HashCode")
    println(userAccount.hashCode())
    println(userAccountCopy.hashCode())
    
}
