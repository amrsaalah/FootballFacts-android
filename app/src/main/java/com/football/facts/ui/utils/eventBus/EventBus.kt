package com.football.facts.ui.utils.eventBus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventBus {

    private val _events = MutableSharedFlow<AppEvent>()
    val events = _events.asSharedFlow()

    suspend fun invokeEvent(event: AppEvent) = _events.emit(event)
}