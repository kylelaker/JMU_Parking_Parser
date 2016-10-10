package com.kylelaker.jmuparking.gui

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

        Timer().scheduleAtFixedRate(timerTask { Platform.runLater { updateLabels() } }, 0, 1000)

        var col = 0
        var row = 0
        stage.title = "JMU Parking Availability"

        val root: GridPane = GridPane()

        root.add(Label("Parking Deck"), col++, row)
        root.add(Label("Spaces available"), col--, row++)

        root.add(Label("Mason St."), col++, row)
        root.add(masonDeckAvailabilityLabel, col--, row++)

        //TODO: Use real spacing/padding. Not this stuff
        root.add(Label("Champions Dr.     "), col++, row)
        root.add(championsDeckAvailabilityLabel, col--, row++)

        root.add(Label("Warsaw Ave."), col++, row)
        root.add(warsawDeckAvailabilityLabel, /*col--, row++*/ col, row)

        stage.scene = Scene(root, 300.0, 250.0)
        stage.show()
    }

    fun updateLabels() {
        val signs = ParsingService.parse()
        masonDeckAvailabilityLabel.text     = signs.single { it.id == PARKING_DECKS.MASON.id     }.output
        championsDeckAvailabilityLabel.text = signs.single { it.id == PARKING_DECKS.CHAMPIONS.id }.output
        warsawDeckAvailabilityLabel.text    = signs.single { it.id == PARKING_DECKS.WARSAW.id    }.output
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) = launch(Gui::class.java)
    }
}
