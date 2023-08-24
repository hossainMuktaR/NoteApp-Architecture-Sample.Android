package com.example.noteapp_architecture_sample.feature_note.domain.add_edit_note_redux

import com.example.noteapp_architecture_sample.feature_note.presentation.add_edit_note.AddEditNoteState
import com.example.noteapp_architecture_sample.feature_note.redux.Container
import com.example.noteapp_architecture_sample.feature_note.redux.Reducer

class AddEditNoteReducer: Reducer<AddEditNoteAction, AddEditNoteState> {
    override fun reduce(
        action: AddEditNoteAction,
        currentState: AddEditNoteState,
    ): AddEditNoteState {
       return when (action) {
            is AddEditNoteAction.ColorChanged -> {
                currentState.copy(
                    noteColor = action.color
                )
            }

            is AddEditNoteAction.TitleFocusChanged -> {
                currentState.copy(
                    isTitleHintVisible = !action.focusState.isFocused &&
                            action.titleValue.isBlank()
                )
            }

            is AddEditNoteAction.ContentFocusChanged -> {
                currentState.copy(
                    isContentHintVisible = !action.focusState.isFocused &&
                            action.contentValue.isBlank()
                )
            }

            is AddEditNoteAction.ContentChanged -> {
                currentState.copy(
                    noteContent = action.value
                )
            }

            is AddEditNoteAction.TitleChanged -> {
                currentState.copy(
                    noteTitle = action.value
                )
            }
            is AddEditNoteAction.FetchNoteComplated -> {
                currentState.copy(
                    noteTitle = action.note.title,
                    noteContent = action.note.content,
                    noteColor = action.note.color,
                    currentNoteid = action.note.id,
                    isTitleHintVisible = false,
                    isContentHintVisible = false,
                )
            }

           else -> currentState
        }
    }
}