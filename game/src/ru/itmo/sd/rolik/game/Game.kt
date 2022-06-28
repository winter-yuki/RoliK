package ru.itmo.sd.rolik.game

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import ru.itmo.sd.rolik.Action
import ru.itmo.sd.rolik.KeyPressedAction
import ru.itmo.sd.rolik.KeyType
import ru.itmo.sd.rolik.Position
import ru.itmo.sd.rolik.Size
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.random.Random

@kotlinx.serialization.Serializable
data class Player(override val position: Position, override val model: Model) : Entity

class StarCraft3(size: Size) : AbstractGame(size) {
    private var level = Level.generate(size)
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
        when (action.key) {
            KeyType.F1 -> save(savePath)
            KeyType.F2 -> load(savePath)
            else -> {}
        }
    }

    @kotlinx.serialization.Serializable
    private data class Save(val level: Level, val player: Player)

    private fun save(path: Path) {
        val save = Save(level, player)
        val string = Json.encodeToString(serializer(), save)
        path.writeText(string)
    }

    private fun load(path: Path) {
        val string = path.readText()
        val load = Json.decodeFromString(serializer<Save>(), string)
        level = load.level
        player = load.player
    }

    companion object {
        private val savePath = Path.of("game.save")
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
