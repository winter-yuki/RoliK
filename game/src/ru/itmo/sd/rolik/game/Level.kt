package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Position
import ru.itmo.sd.rolik.Size

data class Level(override val position: Position, override val model: Model) : Entity {
    private val busy = visiblePositions.toSet()

    override fun contains(position: Position): Boolean = position in busy

    companion object {
        fun generate(fieldSize: Size): Level {
            val position = Position(0, 0)
            val model = MazeGenerator(fieldSize).generate()
            return Level(position, model)
        }
    }
}

private class MazeGenerator(fieldSize: Size) {
    private val size = fieldSize.run {
        copy(
            nRows = (nRows - 1) / 2,
            nCols = (nCols - 1) / 4
        )
    }

    private val maze = Array(size.nCols) { IntArray(size.nRows) }

    fun generate(): Model {
        generate(Position(0, 0))
        return toModel()
    }

    private fun generate(pos: Position) {
        Direction.values().apply { shuffle() }.forEach {
            val new = Position(
                iRow = pos.iRow + it.dRow,
                iCol = pos.iCol + it.dCol
            )
            if (new.inField && maze[new] == 0) {
                maze[pos] = maze[pos] or it.bit
                maze[new] = maze[new] or it.opposite.bit
                generate(new)
            }
        }
    }

    fun toModel(): Model = buildList {
        for (i in 0 until this@MazeGenerator.size.nRows) {
            add(
                buildString {
                    for (j in 0 until this@MazeGenerator.size.nCols) {
                        append(if (maze[j][i] and 1 == 0) "+---" else "+   ")
                    }
                    append('+')
                }
            )
            add(
                buildString {
                    for (j in 0 until this@MazeGenerator.size.nCols) {
                        append(if (maze[j][i] and 8 == 0) "|   " else "    ")
                    }
                    append('|')
                }
            )
        }
        add(
            buildString {
                for (j in 0 until this@MazeGenerator.size.nCols) {
                    append("+---")
                }
                append('+')
            }
        )
    }.let { Model(it) }

    private operator fun Array<IntArray>.get(position: Position): Int =
        get(position.iCol)[position.iRow]

    private operator fun Array<IntArray>.set(position: Position, value: Int) {
        get(position.iCol)[position.iRow] = value
    }

    private val Position.inField: Boolean
        get() = iRow in 0 until size.nRows && iCol in 0 until size.nCols
}

private enum class Direction(val bit: Int, val dCol: Int, val dRow: Int) {
    N(1, 0, -1),
    S(2, 0, 1),
    E(4, 1, 0),
    W(8, -1, 0);

    val opposite: Direction
        get() = when (this) {
            N -> S
            S -> N
            E -> W
            W -> E
        }
}
