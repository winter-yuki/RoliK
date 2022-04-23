package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Action
import ru.itmo.sd.rolik.KeyPressedAction
import ru.itmo.sd.rolik.KeyType
import ru.itmo.sd.rolik.Position
import ru.itmo.sd.rolik.Size
import kotlin.random.Random

data class Player(override val position: Position, override val model: Model) : Entity

class StarCraft3(size: Size) : AbstractGame(size) {
    private val level = Level.generate(size)
    private var player = level.placePlayer(Model('@'))

    override val entities: Iterable<Entity>
        get() = listOf(level, player)

    override fun notify(action: Action) {
        if (action !is KeyPressedAction) return
        val (iRow, iCol) = player.position
        val updated = when (action.key) {
            KeyType.ArrowLeft -> player.copy(position = player.position.copy(iCol = iCol - 1))
            KeyType.ArrowRight -> player.copy(position = player.position.copy(iCol = iCol + 1))
            KeyType.ArrowUp -> player.copy(position = player.position.copy(iRow = iRow - 1))
            KeyType.ArrowDown -> player.copy(position = player.position.copy(iRow = iRow + 1))
            else -> player
        }
        if (updated notIntersects level) {
            player = updated
        }
    }
}

private fun Level.placePlayer(model: Model, nTries: Int = 100, seed: Int = 42): Player {
    val rnd = Random(seed)
    val fieldSize = this.model.size
    repeat(nTries) {
        val pos = Position(
            iRow = rnd.nextInt(fieldSize.nRows),
            iCol = rnd.nextInt(fieldSize.nCols)
        )
        val player = Player(pos, model)
        if (player notIntersects this) {
            return player
        }
    }
    error("Failed to place player on the level")
}
