package com.kylelaker.jmuparking

/**
 * Represents specific data about a sign.
 * [id] is the ID of the sign
 * [display] is what is currently being displayed
 */
data class Sign(val id: Int, val display: String) : Comparable<Sign> {
    val spaces by lazy {
        when {
            display.matches("[0-9]*".toRegex()) -> Integer.parseInt(display)
            display.equals("FULL", true) -> 0
            else -> Int.MIN_VALUE
        }
    }

    val output by lazy {
        when (spaces) {
            Int.MIN_VALUE -> "Unavailable"
            0 -> "Full"
            else -> "$spaces ${if (spaces != 1) "spaces" else "space"} available"
        }
    }

    override fun toString() = "SignId: $id -- Display: $display"
    override fun compareTo(other: Sign) = this.id - other.id
}
