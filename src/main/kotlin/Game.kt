package org.example

import javafx.application.Application
import javafx.stage.Stage
import org.example.GameScene.player


class Game : Application() {
    override fun start(stage: Stage) {
        val gameManager = GameManager()



        GameScene.createScene(stage)
        gameManager.timeGame(stage.scene, player = player)
    }
}