package ru.itmo.sd.rolik.ui

import ru.itmo.sd.rolik.Matrix

/**
 * Represents a single frame of text UI.
 */
@JvmInline
value class Frame(val frame: Matrix)

fun Frame(ss: List<String>): Frame = Frame(Matrix(ss))
