package com.kylelaker.jmuparking

fun main(args: Array<String>) {
    val signs = ParsingService.parse().sorted()

    val MASON_ID     = 24
    val CHAMPIONS_ID = 2
    val WARSAW_ID    = 10

    val mason     = signs.single { it.id == MASON_ID     }
    val champions = signs.single { it.id == CHAMPIONS_ID }
    val warsaw    = signs.single { it.id == WARSAW_ID    }

    println("Mason:     ${mason.output}")
    println("Champions: ${champions.output}")
    println("Warsaw:    ${warsaw.output}")
}
