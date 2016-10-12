package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.ParsingService

enum class PARKING_DECKS(val id: Int) {
    MASON(24), CHAMPIONS(2), WARSAW(10)
}

fun main(args: Array<String>) {
    with(ParsingService.parse()) {
        println("Mason:     ${single { it.id == PARKING_DECKS.MASON.id     }.output}")
        println("Champions: ${single { it.id == PARKING_DECKS.CHAMPIONS.id }.output}")
        println("Warsaw:    ${single { it.id == PARKING_DECKS.WARSAW.id    }.output}")
    }
}
