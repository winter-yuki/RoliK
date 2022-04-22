package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Action
import ru.itmo.sd.rolik.App
import ru.itmo.sd.rolik.KeyPressedAction
import ru.itmo.sd.rolik.KeyType
import ru.itmo.sd.rolik.Position
import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.lanterna.LanternaFactory

class StarCraft3(size: Size) : AbstractGame(size) {
    private var player = Player(Position(2, 3), Model(listOf("@")))

    override val entities: List<Entity>
        get() = listOf(player)

    override fun notify(action: Action) {
        if (action !is KeyPressedAction) return
        val (iRow, iCol) = player.position
        player = when (action.key) {
            KeyType.ArrowLeft -> player.copy(position = player.position.copy(iCol = iCol - 1))
            KeyType.ArrowRight -> player.copy(position = player.position.copy(iCol = iCol + 1))
            KeyType.ArrowUp -> player.copy(position = player.position.copy(iRow = iRow - 1))
            KeyType.ArrowDown -> player.copy(position = player.position.copy(iRow = iRow + 1))
            else -> player
        }
    }
}

data class Player(override val position: Position, override val model: Model) : Entity

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
