package ru.itmo.sd.rolik.ui

import ru.itmo.sd.rolik.Action
import ru.itmo.sd.rolik.Size

/**
 * Represents UI element.
 */
interface Widget {
    fun render(size: Size): Frame
    fun notify(action: Action)
}
