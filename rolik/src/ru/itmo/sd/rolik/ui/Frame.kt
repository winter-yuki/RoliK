package ru.itmo.sd.rolik.ui

import ru.itmo.sd.rolik.Size

/**
 * Represents a single frame of text UI.
 */
@JvmInline
value class Frame(val strings: List<String>) {
    init {
        require(strings.isNotEmpty())
        val length = strings.first().length
        require(length > 0)
        require(strings.all { it.length == length })
    }

    val size: Size
        get() = Size(nRows = strings.size, nCols = strings.first().length)
}
