package ru.itmo.sd.rolik.lanterna

import com.googlecode.lanterna.screen.TerminalScreen
import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.ui.Frame
import ru.itmo.sd.rolik.ui.Screen

/**
 * Lanterna [Screen] implementation.
 */
internal class LanternaScreen(private val screen: TerminalScreen) : Screen {
    private val graphics = screen.newTextGraphics()

    override val size: Size
        get() = Size(nRows = screen.terminalSize.rows, nCols = screen.terminalSize.columns)

    override fun update(frame: Frame) {
        frame.strings.forEachIndexed { i, line ->
            graphics.putString(0, i, line)
        }
        screen.refresh()
    }

    override fun close() {
        screen.terminal.close()
    }
}
