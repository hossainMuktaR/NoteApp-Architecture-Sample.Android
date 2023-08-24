package com.example.noteapp_architecture_sample.feature_note.domain.add_edit_note_redux

import android.util.Log
import com.example.noteapp_architecture_sample.feature_note.domain.model.InvalidNoteException
import com.example.noteapp_architecture_sample.feature_note.domain.model.Note
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.NoteUseCases
import com.example.noteapp_architecture_sample.feature_note.presentation.add_edit_note.AddEditNoteState
import com.example.noteapp_architecture_sample.feature_note.redux.Container
import com.example.noteapp_architecture_sample.feature_note.redux.Middleware

class NoteSaveMiddleware(
    private val noteUseCases: NoteUseCases
): Middleware<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect> {
    override suspend fun process(
        action: AddEditNoteAction,
        currentState: AddEditNoteState,
        container: Container<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect>
    ) {
        when(action) {
            AddEditNoteAction.SaveNote -> {
                val note = Note(
                    title = currentState.noteTitle,
                    content = currentState.noteContent,
                    color = currentState.noteColor,
                    timeStamp = System.currentTimeMillis(),
                    id = currentState.currentNoteid
                )
                try {
                    noteUseCases.addNote(note)
                    container.emitSideEffect(AddEditNoteSideEffect.SaveNote)
                } catch (e: InvalidNoteException){
                    container.emitSideEffect(AddEditNoteSideEffect.ShowSnackbar(
                        message = e.message ?: "Couldn't Save Note"
                    ))
                }
            }
            is AddEditNoteAction.FetchNoteById -> {
                noteUseCases.getNoteById(action.noteId)?.also { note ->
                    container.dispatch(AddEditNoteAction.FetchNoteComplated(note))
                }
            }
            else -> { }
        }
    }

}