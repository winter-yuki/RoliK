package ru.itmo.sd.rolik.ui

import ru.itmo.sd.rolik.Size
import java.io.Closeable

/**
 * Represents app screen.
 */
interface Screen : Closeable {
    val size: Size
    fun update(frame: Frame)
}
