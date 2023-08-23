package com.example.noteapp_architecture_sample.feature_note.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Container(
    initialState: State,
    private val reducer: Reducer,
    private val middleware: List<Middleware> = emptyList()
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val currentState: State
        get() = _state.value

    suspend fun dispatch(action: Action) {
        middleware.forEach { middleware ->  
            middleware.process(action,currentState,this)
        }
        val newState = reducer.reduce( action,currentState)
        _state.value = newState
    }
}