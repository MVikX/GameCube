package org.example

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.Stage


object GameScene {
    val root = Pane()
    val scene = Scene(root, 400.0, 700.0)
    val player = Player()
    val gameManager = GameManager()
    var isGameStart = false
    private val objects = Objects()
    private val startMenu = VBox()
    private val endMenu = VBox()
    private val startButton = Button("Start")
    private val restartButton = Button("Restart")
    private val mainMenuButton = Button("Menu")
    val score = Score()

    fun createScene(stage: Stage) {
        root.children.addAll(startMenu, endMenu)
        //showStartMenu()
        //showEndMenu()
        startGame()
        stage.scene = scene
        stage.show()
    }

    fun startGame() {
        isGameStart = true
        startMenu.isVisible = false
        endMenu.isVisible = false


        //Счетчик
        root.children.add(score.textNode)
        score.textNode.y = 100.0
        score.textNode.x = scene.width / 2

        //Player
        root.children.addAll(player.root)
        player.cubeMove(scene)
        player.cubeSize.x = (scene.width / 2) - (player.cubeSize.width / 2)
        player.cubeSize.y = scene.height - (scene.height / 4)
        gameManager.timeGame(scene, player)


        //Платформа
        root.children.addAll(objects.root)
        objects.floor.x = 0.0
        objects.floor.y = ((scene.height - (scene.height / 4)) + player.cubeSize.height)
        objects.floor.width = scene.width
        objects.floor.height = ((scene.height - (scene.height / 4)) - player.cubeSize.height * 2)
        gameManager.floorY = objects.floor.y


    }

    fun showEndMenu() {
        isGameStart = false

        //End Menu
        endMenu.isVisible = true
        endMenu.alignment = Pos.CENTER
        endMenu.prefWidth = scene.width
        endMenu.prefHeight = scene.height

        //Restart Game
        restartButton.setOnAction { startGame() }
        restartButton.setPrefSize(100.0, 100.0)
        if (!endMenu.children.contains(restartButton)) {
            endMenu.children.add(restartButton)
        }

        //Main Menu
        mainMenuButton.setOnAction { showStartMenu() }
        mainMenuButton.setPrefSize(100.0, 100.0)
        endMenu.spacing = 10.0
        if (!endMenu.children.contains(mainMenuButton)) {
            endMenu.children.add(mainMenuButton)
        }

    }

    private fun showStartMenu() {
        isGameStart = false

        //Start Menu
        startMenu.isVisible = true
        startMenu.alignment = Pos.CENTER
        startMenu.prefWidth = scene.width
        startMenu.prefHeight = scene.height

        //Start Button
        startButton.setOnAction { startGame() }
        startButton.setPrefSize(100.0, 100.0)
        if (!startMenu.children.contains(startButton)) {
            startMenu.children.add(startButton)
        }


        //удаление кнопок

        endMenu.isVisible = false
    }
}