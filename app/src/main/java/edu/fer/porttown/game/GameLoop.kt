package edu.fer.porttown.game

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GameLoop(private var scope: CoroutineScope) {
    private val _ticker = MutableSharedFlow<Unit>()
    val ticker: SharedFlow<Unit> = _ticker
    private lateinit var job: Job

    fun start() {
        job = scope.launch {
            while (true) {
                _ticker.emit(Unit)
                delay(Config.MS_PER_TICK)
            }
        }
    }

    fun stop() {
        job.cancel()
    }
}