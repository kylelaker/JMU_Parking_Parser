package com.kylelaker.jmuparking.implexample.gui

import com.kylelaker.jmuparking.ParsingService
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import java.util.*
import kotlin.concurrent.timerTask

enum class PARKING_DECKS(val id: Int) {
    MASON(24), CHAMPIONS(2), WARSAW(10)
}

class Gui : Application() {

    val masonDeckAvailabilityLabel = Label()
    val championsDeckAvailabilityLabel = Label()
    val warsawDeckAvailabilityLabel = Label()

    override fun start(stage: Stage) {

        var col = 0
        var row = 0
        stage.title = "JMU Parking Availability"

        val root: GridPane = GridPane()
        with(root) {
            add(Label("Parking Deck"), col++, row)
            add(Label("Spaces available"), col--, row++)
            add(Label("Mason St."), col++, row)
            add(masonDeckAvailabilityLabel, col--, row++)
            add(Label("Champions Dr.     "), col++, row)
            add(championsDeckAvailabilityLabel, col--, row++)
            add(Label("Warsaw Ave."), col++, row)
            add(warsawDeckAvailabilityLabel, /*col--, row++*/ col, row)
        }

        Timer().scheduleAtFixedRate(timerTask {
            Platform.runLater {
                with(ParsingService.parse()) {
                    masonDeckAvailabilityLabel.text     = single { it.id == PARKING_DECKS.MASON.id     }.output
                    championsDeckAvailabilityLabel.text = single { it.id == PARKING_DECKS.CHAMPIONS.id }.output
                    warsawDeckAvailabilityLabel.text    = single { it.id == PARKING_DECKS.WARSAW.id    }.output
                }
            }
        }, 0, 1000)

        stage.scene = Scene(root, 300.0, 250.0)
        stage.show()
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) = launch(Gui::class.java)
    }
}
