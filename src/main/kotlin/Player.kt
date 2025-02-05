package org.example

import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import org.example.GameScene.scene


class Player {
    val root = Pane()
    private var speed = 0.0
    val cubeSize = Rectangle(0.0, 0.0, 50.0, 50.0).apply { fill = Color.BLACK }
    private val activeKey = mutableListOf<KeyCode>()



    fun cubeMove(scene: Scene) {
        scene.setOnKeyPressed { event ->
            if (event.code == KeyCode.A || event.code == KeyCode.D) {
                if (!activeKey.contains(event.code)) {
                    activeKey.add(event.code)
                }
            }
            when (event.code) {
                KeyCode.A -> speed = -200.0
                KeyCode.D -> speed = 200.0
                else -> {}
            }
        }
        scene.setOnKeyReleased { event ->
            activeKey.remove(event.code)
            if (activeKey.isEmpty()) {
                speed = 0.0
            }
        }
    }

    fun update(deltaTime: Double) {
        cubeSize.x += speed*deltaTime
        if (cubeSize.x <= 0) cubeSize.x = 0.0
        if (cubeSize.x > scene.width - cubeSize.width) cubeSize.x = scene.width - cubeSize.width
    }




    init {
        root.children.add(cubeSize)
    }
}