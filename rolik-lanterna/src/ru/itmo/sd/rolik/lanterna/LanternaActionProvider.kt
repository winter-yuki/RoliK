package ru.itmo.sd.rolik.lanterna

import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.TerminalScreen
import ru.itmo.sd.rolik.Action
import ru.itmo.sd.rolik.ActionProvider
import ru.itmo.sd.rolik.KeyPressedAction

/**
 * Lanterna [ActionProvider] implementation.
 */
internal class LanternaActionProvider(private val screen: TerminalScreen) : ActionProvider {
    override fun next(): Action? {
        val input = screen.pollInput()?.keyType ?: return null
        val key = when (input) {
            KeyType.ArrowUp -> ru.itmo.sd.rolik.KeyType.ArrowUp
            KeyType.ArrowDown -> ru.itmo.sd.rolik.KeyType.ArrowDown
            KeyType.ArrowLeft -> ru.itmo.sd.rolik.KeyType.ArrowLeft
            KeyType.ArrowRight -> ru.itmo.sd.rolik.KeyType.ArrowRight
            KeyType.EOF -> ru.itmo.sd.rolik.KeyType.EOF
            KeyType.Escape -> ru.itmo.sd.rolik.KeyType.ESC
            else -> return null
        }
        return KeyPressedAction(key)
    }
}
