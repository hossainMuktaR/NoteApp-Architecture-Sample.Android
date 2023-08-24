package com.example.noteapp_architecture_sample.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp_architecture_sample.feature_note.domain.add_edit_note_redux.AddEditNoteSideEffect
import com.example.noteapp_architecture_sample.feature_note.domain.add_edit_note_redux.AddEditNoteAction
import com.example.noteapp_architecture_sample.feature_note.domain.add_edit_note_redux.AddEditNoteContainer
import com.example.noteapp_architecture_sample.feature_note.domain.model.Note
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.NoteUseCases
import com.example.noteapp_architecture_sample.feature_note.redux.Container
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    noteUseCases: NoteUseCases
) : ViewModel() {

    private val container = AddEditNoteContainer(noteUseCases)
    val state: StateFlow<AddEditNoteState> = container.state
    val sideEffect: SharedFlow<AddEditNoteSideEffect> = container.sideEffect


    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                fetchNoteById(noteId)
            }
        }
    }

    private fun fetchNoteById(noteId: Int) = intent {
        dispatch(AddEditNoteAction.FetchNoteById(noteId))
    }
    fun colorChanged(color: Int) = intent {
        dispatch(AddEditNoteAction.ColorChanged(color))
    }

    fun titleChanged(value: String) = intent {
        dispatch(AddEditNoteAction.TitleChanged(value))
    }

    fun titleFocusChanged(focusState: FocusState, titleValue: String) = intent {
        dispatch(AddEditNoteAction.TitleFocusChanged(focusState, titleValue))
    }

    fun contentChanged(value: String) = intent {
        dispatch(AddEditNoteAction.ContentChanged(value))
    }

    fun contentFocusChanged(focusState: FocusState, contentValue: String) = intent {
        dispatch(AddEditNoteAction.ContentFocusChanged(focusState, contentValue))
    }

    fun saveNote() = intent {
        dispatch(AddEditNoteAction.SaveNote)
    }

    private fun intent(transform: suspend Container<AddEditNoteAction, AddEditNoteState, AddEditNoteSideEffect>.() -> Unit) {
        viewModelScope.launch {
            container.transform()
        }
    }
}
