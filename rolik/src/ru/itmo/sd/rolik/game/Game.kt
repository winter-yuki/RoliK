package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.ui.Frame
import ru.itmo.sd.rolik.ui.Widget

interface Game : Widget

abstract class AbstractGame(private val size: Size) : Game {
    abstract val entities: List<Entity>

    override fun render(size: Size): Frame {
        if (size != this.size) TODO("Focus view on some entry and crop")
        val field = MutableList(size.nRows) { MutableList(size.nCols) { ' ' } }
        entities.forEach { entity ->
            val matrix = entity.model.repr.value
            matrix.forEachIndexed { iRow, line ->
                line.forEachIndexed { iCol, c ->
                    field[entity.position.iRow + iRow][entity.position.iCol + iCol] = c
                }
            }
        }
        return Frame(field.map { it.joinToString("") })
    }
}
