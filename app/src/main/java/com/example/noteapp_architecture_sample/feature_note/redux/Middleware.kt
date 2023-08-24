package com.example.noteapp_architecture_sample.feature_note.redux

import android.view.WindowInsets.Side

interface Middleware<A: Action, S: State, SE: SideEffect> {
    suspend fun process(
        action: A,
        currentState: S,
        container: Container<A, S, SE>,
        )
}