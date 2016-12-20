package com.kylelaker.jmuparking.implexample

import com.kylelaker.jmuparking.Sign

enum class PARKING_DECK(val id: Int) {
    MASON(24), CHAMPIONS(2), WARSAW(10)
}

fun outputString(deck: PARKING_DECK, set:Set<Sign>) =
        set.first { it.id == deck.id }.output
