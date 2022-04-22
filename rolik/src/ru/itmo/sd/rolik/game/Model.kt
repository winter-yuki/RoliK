package ru.itmo.sd.rolik.game

import ru.itmo.sd.rolik.Matrix

/**
 * Model of the [Entity] to be rendered.
 */
@JvmInline
value class Model(val repr: Matrix)

fun Model(ss: List<String>): Model = Model(Matrix(ss))
