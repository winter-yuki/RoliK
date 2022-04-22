package ru.itmo.sd.rolik

import kotlin.time.Duration

/**
 * [Actions][Action] are results of interaction between
 * the actor outside (user/server...) and the game.
 */
interface Action

/**
 * Provides next action for processing if available.
 */
interface ActionProvider {
    fun next(): Action?
}

enum class KeyType {
    ArrowUp, ArrowDown, ArrowLeft, ArrowRight, ESC, EOF
}

data class KeyPressedAction(val key: KeyType) : Action

data class TimerAction(val elapsed: Duration) : Action
