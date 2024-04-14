package Model

import org.example.Model.Game
import java.util.*
import kotlin.random.Random

data class Gamer(var name:String, var email:String){
    var dateOfBirth:String? = null
    var user:String? = null
        set(value){
            field = value
            if(innerId.isNullOrBlank()){
                createInnerId()
            }
        }
    var innerId:String? = null
        private set

    constructor(name: String, email: String, dateOfBirth:String, user:String):
            this(name, email){
        this.dateOfBirth = dateOfBirth
        this.user = user
        createInnerId()
    }

    init {
        if (name.isNullOrBlank()){
            throw IllegalArgumentException("Name is blank!")
        }
        this.email = validateEmail()
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', dateOfBirth=$dateOfBirth, user=$user, innerId=$innerId)"
    }

    fun createInnerId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        innerId = "$user#$tag"
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if(regex.matches(email)){
            return email
        }else{
            throw IllegalArgumentException("Invalid email!")
        }
    }

    companion object{
        fun createGamer(read: Scanner):Gamer{
            println("Welcome to AluGames! Let's register. Enter your name:")
            val name = read.nextLine()
            println("Type your e-mail:")
            val email = read.nextLine()
            println("Do you want to complete your registration with username and date of birth? (Y/N)")
            val option = read.nextLine()

            if (option.equals("y", true)){
                println("Enter your date of birth (DD/MM/YYYY):")
                val dateOfBirth = read.nextLine()
                println("Enter your username:")
                val user = read.nextLine()

                return Gamer(name, email, dateOfBirth, user)
            }else{
                return Gamer(name, email)
            }
        }
    }



}
