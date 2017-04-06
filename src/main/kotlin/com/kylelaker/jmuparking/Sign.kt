package com.kylelaker.jmuparking

/**
 * Represents specific data about a sign.
 * [id] is the ID of the sign
 * [displayText] is what is currently being displayed
 */
class Sign(val id: Int, val displayText: String) : Comparable<Sign> {
    val spaces by lazy {
        when {
            displayText.matches("[\\d]+".toRegex()) -> displayText.toInt()
            displayText.equals("FULL", true) -> 0
            else -> Int.MIN_VALUE
        }
    }

    val output by lazy {
        when (spaces) {
            Int.MIN_VALUE -> "Unavailable"
            0 -> "Full"
            else -> "$spaces"
        }
    }

    override fun toString() = "SignId: $id -- Display: $displayText"
    override fun compareTo(other: Sign) = this.id - other.id
    override fun equals(other: Any?) =
            other is Sign && id == other.id && displayText == other.displayText
    override fun hashCode() = (31 * id) + displayText.hashCode()

}
