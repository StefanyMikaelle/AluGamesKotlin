package Main

import Model.Gamer

fun main(){
    val gamer1 = Gamer("Stefany","stefany_mikaelle@hotmail.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Pedro",
        "pedro@email.com",
        "10/05/2022",
        "pedroGamer"
    )

    gamer1.let {
        it.dateOfBirth = "08/08/1995"
        it.user="stefanyGamer"
    }.also {
        println(gamer1.innerId)
    }

    println(gamer1)
    gamer1.user = "Ste"
    println(gamer1)
}