package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.ParsingService
import com.kylelaker.jmuparking.implexample.PARKING_DECK.*

fun main(args: Array<String>) {
    while (true) {
        clearScreen()
        println("JMU Parking Availability")
        println("Deck       Spaces Available")
        with(ParsingService.parse()) {
            println("${MASON.deckName}:     ${outputString(MASON, this)}")
            println("${CHAMPIONS.deckName}: ${outputString(CHAMPIONS, this)}")
            println("${WARSAW.deckName}:    ${outputString(WARSAW, this)}")
        }

        Thread.sleep(1000)
    }
}

fun clearScreen() {
    // Typing \033[H\033[2J results in an "invalid escape character" message in Kotlin. So I
    // executed `echo "\033" | pbcopy` on the command line and pasted that into the variable. This
    // works well enough for now. 🤷🏻‍♂️
    val ansi033 = "";
    print("$ansi033[H$ansi033[2J")
    System.out.flush()
}
