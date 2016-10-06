package com.kylelaker.jmuparking

/**
 * Represents specific data about a sign.
 * [id] is the ID of the sign
 * [display] is what is currently being displayed
 */
data class Sign(val id: Int, val display: String) : Comparable<Sign> {
    val spaces by lazy {
        if (display.matches("[0-9]*".toRegex())) {
            Integer.parseInt(display)
        } else {
            -1
        }
    }

    val output by lazy {
        if (spaces == -1 && display.equals("FULL", ignoreCase = true)) {
            "FULL"
        } else if (spaces == -1) {
            "Unavailable"
        } else {
            String.format("%d spaces available", spaces)
        }
    }

    override fun toString() = "ID: $id -- Display: $display"

    override fun compareTo(other: Sign) = this.id - other.id
}
