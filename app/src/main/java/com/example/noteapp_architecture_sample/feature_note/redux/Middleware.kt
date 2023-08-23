package com.example.noteapp_architecture_sample.feature_note.redux

interface Middleware {
    suspend fun process(action: Action, currentState: State, container: Container)
}