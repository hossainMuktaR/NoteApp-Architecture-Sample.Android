package com.example.noteapp_architecture_sample.feature_note.redux

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

open class BaseContainer<A: Action, S: State, SE: SideEffect>(
    initialState: S,
    private val reducer: Reducer<A, S>,
    private val middleware: List<Middleware<A, S, SE>> = emptyList()
): Container<A, S, SE> {
    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<S> = _state

    private val _sideEffect = MutableSharedFlow<SE>()
    override val sideEffect: SharedFlow<SE> = _sideEffect.asSharedFlow()
    private val currentState: S
        get() = _state.value

    override suspend fun dispatch(action: A) {
        middleware.forEach { middleware ->
            middleware.process(action,currentState,this)
        }
        val newState = reducer.reduce(action,currentState)
        _state.value = newState
    }

    override suspend fun emitSideEffect(sideEffect: SE) {
        _sideEffect.emit(sideEffect)
    }

}