package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.ui.Frame
import ru.itmo.sd.rolik.ui.Widget

interface Game : Widget

abstract class AbstractGame(private val fieldSize: Size) : Game {
    abstract val entities: Iterable<Entity>

    override fun render(size: Size): Frame {
        // Can be removed by focusing view on some entry and crop
        require(size == fieldSize) { "All field should be visible" }
        val frame = MutableList(size.nRows) { MutableList(size.nCols) { ' ' } }
        entities.forEach { entity ->
            val matrix = entity.model.strings
            matrix.forEachIndexed rows@{ dRow, line ->
                val iRow = entity.position.iRow + dRow
                if (iRow < 0 || iRow >= size.nRows) return@rows
                line.forEachIndexed cols@{ dCol, c ->
                    val iCol = entity.position.iCol + dCol
                    if (iCol < 0 || iCol >= size.nCols) return@cols
                    frame[iRow][iCol] = c
                }
            }
        }
        return Frame(frame.map { it.joinToString("") })
    }
}
