package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.Sign

enum class PARKING_DECK(val id: Int, val deckName: String) {
    MASON(24, "Mason"), CHAMPIONS(2, "Champions"), WARSAW(10, "Warsaw")
}

fun outputString(deck: PARKING_DECK, set:Set<Sign>) =
        set.single { it.id == deck.id }.output
