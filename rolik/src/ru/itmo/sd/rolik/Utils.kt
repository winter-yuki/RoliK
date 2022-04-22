package ru.itmo.sd.rolik

import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Repeat [block] executions until [duration] time elapses, making [interval] delays between.
 */
suspend inline fun repeat(duration: Duration, interval: Duration = 0.milliseconds, block: () -> Unit) {
    val start = System.currentTimeMillis()
    val end = start + duration.inWholeMilliseconds
    while (System.currentTimeMillis() < end) {
        block()
        delay(interval)
    }
}

data class Position(val iRow: Int, val iCol: Int)

data class Size(val nRows: Int, val nCols: Int) {
    init {
        require(nRows > 0)
        require(nCols > 0)
    }
}

@JvmInline
value class Matrix(val value: List<String>) {
    init {
        require(value.isNotEmpty())
        val length = value.first().length
        require(length > 0)
        require(value.all { it.length == length })
    }
}
