package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.App
import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.lanterna.LanternaFactory

suspend fun main() {
    val size = Size(nRows = 30, nCols = 80)
    val game = StarCraft3(size)
    val factory = LanternaFactory("StarCraft3", size)
    val app = App(
        root = game,
        screen = factory.createScreen(),
        actionProvider = factory.createActionProvider()
    )
    app.run()
}
