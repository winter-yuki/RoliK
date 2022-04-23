package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Position

/**
 * Represents element of the game that can be rendered on the screen.
 */
interface Entity {
    val position: Position
    val model: Model

    operator fun contains(position: Position): Boolean {
        val inRow = position.iRow in this.position.iRow until this.position.iRow + model.size.nRows
        val inCol = position.iCol in this.position.iCol until this.position.iCol + model.size.nCols
        return inRow && inCol
    }

    infix fun intersects(entity: Entity): Boolean =
        visiblePositions.any { it in entity }
}

val Entity.visiblePositions: Iterable<Position>
    get() = model.strings.asSequence().flatMapIndexed { iRow: Int, row: String ->
        row.asSequence().mapIndexedNotNull { iCol, c ->
            if (c.isWhitespace()) null
            else Position(iRow = position.iRow + iRow, iCol = position.iCol + iCol)
        }
    }.asIterable()

infix fun Entity.notIntersects(entity: Entity): Boolean = !intersects(entity)
