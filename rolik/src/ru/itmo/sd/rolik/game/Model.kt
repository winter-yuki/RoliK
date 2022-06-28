package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Size

/**
 * Model of the [Entity] to be rendered.
 */
@kotlinx.serialization.Serializable
@JvmInline
value class Model(val strings: List<String>) {
    val size: Size
        get() = Size(nRows = strings.size, nCols = strings.first().length)
}

fun Model(s: String): Model = Model(listOf(s))

fun Model(c: Char): Model = Model(c.toString())
