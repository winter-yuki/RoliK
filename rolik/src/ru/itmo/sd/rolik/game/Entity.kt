package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Position

/**
 * Represents element of the game that can be rendered on the screen.
 */
interface Entity {
    val position: Position
    val model: Model
}
