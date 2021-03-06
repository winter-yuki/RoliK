package ru.itmo.sd.rolik

import ru.itmo.sd.rolik.ui.Screen
import ru.itmo.sd.rolik.ui.Widget
import kotlin.time.Duration.Companion.milliseconds

/**
 * Represents game application.
 */
class App(
    private val root: Widget,
    private val screen: Screen,
    private val actionProvider: ActionProvider,
    private val fps: Int = 60,
    private val exitActions: Set<Action> = setOf(
        KeyPressedAction(KeyType.ESC),
        KeyPressedAction(KeyType.EOF)
    ),
) {
    init {
        require(fps > 0)
    }

    suspend fun run() = screen.use {
        loop()
    }

    private suspend fun loop() {
        var lastNotification = System.currentTimeMillis().milliseconds
        while (true) {
            repeat(
                duration = (MILLIS_IN_SECS / fps).milliseconds,
                interval = 50.milliseconds
            ) {
                val time = System.currentTimeMillis().milliseconds
                val timer = TimerAction(time - lastNotification)
                root.notify(timer)
                lastNotification = time

                val action = actionProvider.next() ?: return@repeat
                if (action in exitActions) return
                root.notify(action)
            }
            val frame = root.render(screen.size)
            screen.update(frame)
        }
    }

    companion object {
        private const val MILLIS_IN_SECS = 1000
    }
}
