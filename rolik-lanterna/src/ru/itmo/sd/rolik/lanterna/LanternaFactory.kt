package ru.itmo.sd.rolik.lanterna

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.itmo.sd.rolik.ActionProvider
import ru.itmo.sd.rolik.Size
import ru.itmo.sd.rolik.ui.Screen

/**
 * Creates lanterna implementations.
 */
class LanternaFactory(title: String, size: Size) {
    private val screen: TerminalScreen

    init {
        val factory = DefaultTerminalFactory().apply {
            setInitialTerminalSize(TerminalSize(size.nCols, size.nRows))
            setTerminalEmulatorTitle(title)
        }
        val terminal = factory.createTerminal()
        screen = TerminalScreen(terminal).apply {
            // Move cursor away
            cursorPosition = TerminalPosition(size.nCols + 1, size.nRows + 1)
            startScreen()
            clear()
        }
    }

    fun createScreen(): Screen = LanternaScreen(screen)
    fun createActionProvider(): ActionProvider = LanternaActionProvider(screen)
}
