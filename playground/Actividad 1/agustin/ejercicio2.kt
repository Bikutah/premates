fun main() {

    val user1 = userAccount("1","agustin@gmail.com",10.0) 
    
    println(user1)
}

data class userAccount(val uuid: String, val email: String, val balance: Double)


