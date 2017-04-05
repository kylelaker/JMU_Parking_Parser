package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.ParsingService
import com.kylelaker.jmuparking.implexample.PARKING_DECK.*

fun main(args: Array<String>) {
    with(ParsingService.parse()) {
        println("${MASON.deckName}:     ${outputString(MASON, this)}")
        println("${CHAMPIONS.deckName}: ${outputString(CHAMPIONS, this)}")
        println("${WARSAW.deckName}:    ${outputString(WARSAW, this)}")
    }
}
