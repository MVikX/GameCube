package org.example

import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text

class Score {
    var scoreGame = 0


    val textNode = Text("$scoreGame").apply {
        fill = Color.BLACK
        font = Font.font(50.0)
    }

    val textGameOver = Text("LOSE. You score: $scoreGame").apply {
        fill = Color.BLACK
        font = Font.font(100.0)
    }


    fun updateScore() {
        textNode.text = "$scoreGame"
    }
}