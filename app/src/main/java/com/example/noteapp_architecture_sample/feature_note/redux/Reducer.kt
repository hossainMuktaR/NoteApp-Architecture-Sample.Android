package com.example.noteapp_architecture_sample.feature_note.redux

interface Reducer {

    fun reduce(action: Action, currentState: State): State
}