package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.ParsingService

enum class PARKING_DECKS(val id: Int) {
    MASON(24), CHAMPIONS(2), WARSAW(10)
}

fun main(args: Array<String>) {
    val signs = ParsingService.parse().sorted()


    val mason = signs.single { it.id == PARKING_DECKS.MASON.id }.output
    val champions = signs.single { it.id == PARKING_DECKS.CHAMPIONS.id }.output
    val warsaw = signs.single { it.id == PARKING_DECKS.WARSAW.id }.output

    println("Mason:     $mason")
    println("Champions: $champions")
    println("Warsaw:    $warsaw")
}
