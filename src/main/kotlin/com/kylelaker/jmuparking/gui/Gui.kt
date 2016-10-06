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

class Gui : Application() {
    val MASON_ID     = 24
    val CHAMPIONS_ID = 2
    val WARSAW_ID    = 10

    val masonDeckAvailabilityLabel = Label()
    val championsDeckAvailabilityLabel = Label()
    val warsawDeckAvailabilityLabel = Label()

    override fun start(stage: Stage) {
        var col = 0;
        var row = 0;
        stage.title = "JMU Parking Availability"

        val root: GridPane = GridPane()

        root.add(Label("Parking Deck"), col++, row)
        root.add(Label("Spaces available"), col--, row++)

        root.add(Label("Mason St."), col++, row)
        root.add(masonDeckAvailabilityLabel, col--, row++)

        root.add(Label("Champions Dr.     "), col++, row)
        root.add(championsDeckAvailabilityLabel, col--, row++)

        root.add(Label("Warsaw Ave."), col++, row)
        root.add(warsawDeckAvailabilityLabel, /*col--, row++*/ col, row)

        stage.scene = Scene(root, 300.0, 250.0)
        stage.show()

        Timer().scheduleAtFixedRate(timerTask { Platform.runLater { updateLabels() } }, 0, 2000)
    }

    fun updateLabels() {
        val signs = ParsingService.parse().sorted()
        masonDeckAvailabilityLabel.text = signs.single { it.id == MASON_ID }.output
        championsDeckAvailabilityLabel.text = signs.single { it.id == CHAMPIONS_ID }.output
        warsawDeckAvailabilityLabel.text = signs.single { it.id == WARSAW_ID }.output
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Gui::class.java)
        }
    }
}
