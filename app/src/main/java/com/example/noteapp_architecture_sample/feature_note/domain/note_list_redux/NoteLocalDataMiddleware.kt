package com.example.noteapp_architecture_sample.feature_note.domain.note_list_redux

import com.example.noteapp_architecture_sample.feature_note.domain.use_case.NoteUseCases
import com.example.noteapp_architecture_sample.feature_note.presentation.note_list.NoteListState
import com.example.noteapp_architecture_sample.feature_note.redux.Container
import com.example.noteapp_architecture_sample.feature_note.redux.Middleware

class NoteLocalDataMiddleware (
    private val noteUseCases: NoteUseCases
): Middleware<NoteListAction, NoteListState, NoteListSideEffect> {
    override suspend fun process(
        action: NoteListAction,
        currentState: NoteListState,
        container: Container<NoteListAction, NoteListState, NoteListSideEffect>
    ) {
        when (action){
            is NoteListAction.FetchOrderedNoteList -> {
                val notes = noteUseCases.getAllNotes(action.order)
                container.dispatch(NoteListAction.AllNoteLoaded(notes, action.order))
            }
            is NoteListAction.DeleteNote -> {
                noteUseCases.deleteNote(action.note)
                container.dispatch(NoteListAction.FetchOrderedNoteList(action.order))
                container.emitSideEffect(NoteListSideEffect.DeleteNote("note Deleted"))
            }
            is NoteListAction.RestoreNote -> {
                noteUseCases.addNote(action.note)
                container.dispatch(NoteListAction.FetchOrderedNoteList(action.order))
            }
            else -> { }
        }
    }

}