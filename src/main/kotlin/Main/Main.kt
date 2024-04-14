package org.example.Main

import Services.SearchApi
import com.google.gson.Gson
import org.example.Model.Game
import org.example.Model.InfoGame
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

fun main() {
    val read = Scanner(System.`in`)
    println("Enter a game code to search:")
    val search = read.nextLine()

    val searchApi = SearchApi()
    val gameInformation = searchApi.searchGame(search)

    var myGame : Game? = null

    val result = runCatching {
        myGame = Game (
            gameInformation.info.title,
            gameInformation.info.thumb
        )
    }

    result.onFailure {
        println("Game not found. Please try another ID.")
    }

    result.onSuccess {
        println("Do you want to input a custom description? Y/N")
        val option = read.nextLine()
        if(option.equals("y", true)){
            println("Enter the custom description for the game:")
            val customDescription = read.nextLine()
            myGame?.description = customDescription
        }else{
            myGame?.description = myGame?.title
        }

        println(myGame)
    }

    result.onSuccess {
        println("Search completed sucessfully.")
    }
}