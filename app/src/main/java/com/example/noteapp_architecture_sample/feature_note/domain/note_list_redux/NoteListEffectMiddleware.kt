package com.example.noteapp_architecture_sample.feature_note.domain.note_list_redux

import com.example.noteapp_architecture_sample.feature_note.presentation.note_list.NoteListState
import com.example.noteapp_architecture_sample.feature_note.redux.Container
import com.example.noteapp_architecture_sample.feature_note.redux.Middleware

class NoteListEffectMiddleware : Middleware<NoteListAction, NoteListState, NoteListSideEffect> {
    override suspend fun process(
        action: NoteListAction,
        currentState: NoteListState,
        container: Container<NoteListAction, NoteListState, NoteListSideEffect>
    ) {
        when (action){
            NoteListAction.GoAddEditScreen -> {
                container.emitSideEffect(NoteListSideEffect.NavigateAddNoteScreen)
            }
            is NoteListAction.GoNoteUpdateScreen -> {
                container.emitSideEffect(NoteListSideEffect.GoNoteEditScreen(action.noteId, action.noteColor))
            }
            else -> { }
        }
    }

}