package org.example

import javafx.animation.AnimationTimer
import javafx.geometry.Bounds
import javafx.scene.Scene
import javafx.scene.shape.Circle
import org.example.GameScene.isGameStart

class GameManager {
    private var lastTime = System.nanoTime()
    private lateinit var loopGame: AnimationTimer
    private val objects = Objects()
    private val intervalSpawn = 0.5
    private var timerSpawn = 0.0
    private val ballSpeed = 200.0
    private val allBalls = mutableListOf<Circle>()
    var floorY: Double = 0.0

    fun timeGame(game: Scene, player: Player) {
        loopGame = object : AnimationTimer() {
            override fun handle(now: Long) {
                val deltaTime = (now - lastTime) / 1_000_000_000.0
                lastTime = now

                // Таймер спавна шаров
                if (isGameStart) {
                    timerSpawn += deltaTime
                }
                if (timerSpawn >= intervalSpawn) {
                    val newBall = objects.createBall()
                    GameScene.root.children.add(newBall)
                    allBalls.add(newBall)
                    timerSpawn = 0.0
                }

                // Обновление движения шаров
                updateBalls(deltaTime)

                // Проверка столкновений с полом
                checkCollision()

                player.update(deltaTime)
            }
        }
        loopGame.start()
    }

    // Обновление падения шаров
    private fun updateBalls(deltaTime: Double) {
        for (ball in allBalls) {
            ball.centerY += ballSpeed * deltaTime
        }
    }

    // Проверка столкновений с полом
    private fun checkCollision() {
        val iterator = allBalls.iterator()
        while (iterator.hasNext()) {
            val ball = iterator.next()
            val ballBounds: Bounds = ball.boundsInParent
            val floorBounds: Bounds = objects.floor.boundsInParent

            // Проверка столкновения с полом
            if (ballBounds.intersects(floorBounds)) {
                GameScene.root.children.remove(ball)
                iterator.remove()
                GameScene.score.scoreGame++
                GameScene.score.updateScore()
            }
        }
    }
}
