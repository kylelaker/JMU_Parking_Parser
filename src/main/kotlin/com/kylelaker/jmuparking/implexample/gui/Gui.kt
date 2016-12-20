package com.kylelaker.jmuparking.implexample.gui

import com.kylelaker.jmuparking.ParsingService
import com.kylelaker.jmuparking.implexample.PARKING_DECK
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

        val root = GridPane()
        with(root) {
            hgap = 10.0
            add(nameLabel, col++, row)
            add(spacesLabel, col--, row++)
            add(Label("Mason St."), col++, row)
            add(masonDeckAvailabilityLabel, col--, row++)
            add(Label("Champions Dr."), col++, row)
            add(championsDeckAvailabilityLabel, col--, row++)
            add(Label("Warsaw Ave."), col++, row)
            add(warsawDeckAvailabilityLabel, col--, row++)
        }

        Timer().scheduleAtFixedRate(timerTask {
            Platform.runLater {
                val data = ParsingService.parse()
                masonDeckAvailabilityLabel.text     = outputString(PARKING_DECK.MASON,     data)
                championsDeckAvailabilityLabel.text = outputString(PARKING_DECK.CHAMPIONS, data)
                warsawDeckAvailabilityLabel.text    = outputString(PARKING_DECK.WARSAW,    data)
            }
        }, 0, 1000)

        with(stage) {
            title = "JMU Parking Availability"
            scene = Scene(root, 235.0, 75.0)
            isAlwaysOnTop = true
            isResizable = false
            Platform.setImplicitExit(true)
            show()
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) = launch(Gui::class.java)
    }
}
