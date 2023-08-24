package com.example.noteapp_architecture_sample.feature_note.domain.note_list_redux

import com.example.noteapp_architecture_sample.feature_note.domain.use_case.NoteUseCases
import com.example.noteapp_architecture_sample.feature_note.presentation.note_list.NoteListState
import com.example.noteapp_architecture_sample.feature_note.redux.BaseContainer

class NoteListContainer(
    noteUseCases: NoteUseCases
): BaseContainer<NoteListAction, NoteListState, NoteListSideEffect> (
    initialState = NoteListState(),
    reducer = NoteListReducer(),
    middleware = listOf(
        NoteListEffectMiddleware(),
        NoteLocalDataMiddleware(noteUseCases)
    ),
)