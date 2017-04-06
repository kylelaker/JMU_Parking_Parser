package com.kylelaker.jmuparking.implexample.gui

import com.kylelaker.jmuparking.ParsingService
import com.kylelaker.jmuparking.implexample.PARKING_DECK.*
import com.kylelaker.jmuparking.implexample.outputString
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import java.util.*
import kotlin.concurrent.timerTask

class Gui : Application() {

    override fun start(stage: Stage) {

        val nameLabel = Label("Parking deck")
        val spacesLabel = Label("Spaces available")
        val masonDeckAvailabilityLabel = Label()
        val championsDeckAvailabilityLabel = Label()
        val warsawDeckAvailabilityLabel = Label()
        var col = 0
        var row = 0

        Timer().scheduleAtFixedRate(timerTask {
            Platform.runLater {
                with (ParsingService.parse()) {
                    masonDeckAvailabilityLabel.text     = outputString(MASON,     this)
                    championsDeckAvailabilityLabel.text = outputString(CHAMPIONS, this)
                    warsawDeckAvailabilityLabel.text    = outputString(WARSAW,    this)
                }
            }
        }, 0, 1000)

        Platform.setImplicitExit(true)
        stage.apply {
            title = "JMU Parking Availability"
            scene = Scene(GridPane().apply {
                hgap = 10.0
                //Pattern is: (a) col++, row; (b) col--, row++
                add(nameLabel, col++, row)
                add(spacesLabel, col--, row++)
                add(Label(MASON.deckName), col++, row)
                add(masonDeckAvailabilityLabel, col--, row++)
                add(Label(CHAMPIONS.deckName), col++, row)
                add(championsDeckAvailabilityLabel, col--, row++)
                add(Label(WARSAW.deckName), col++, row)
                add(warsawDeckAvailabilityLabel, col--, row++)
            }, 235.0, 75.0)
            isAlwaysOnTop = true
            isResizable = false
        }.show()

    }

    companion object {
        @JvmStatic fun main(args: Array<String>) = launch(Gui::class.java)
    }
}
