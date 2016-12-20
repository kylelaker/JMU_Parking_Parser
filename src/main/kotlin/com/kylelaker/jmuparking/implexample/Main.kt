package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.ParsingService

fun main(args: Array<String>) {
    with(ParsingService.parse()) {
        println("Mason:     ${single { it.id == PARKING_DECK.MASON.id     }.output}")
        println("Champions: ${single { it.id == PARKING_DECK.CHAMPIONS.id }.output}")
        println("Warsaw:    ${single { it.id == PARKING_DECK.WARSAW.id    }.output}")
    }
}
