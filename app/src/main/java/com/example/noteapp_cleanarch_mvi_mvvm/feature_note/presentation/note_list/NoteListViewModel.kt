package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.note_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.use_case.NoteUseCases
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils.NoteOrder
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf<NoteListState>(NoteListState())
    val state: State<NoteListState> = _state

    private val _uiIntentFlow = MutableSharedFlow<UiIntent>()
    val uiIntentFlow: SharedFlow<UiIntent> = _uiIntentFlow

    private var recentlyDeletedNote: Note? = null
    private var noteJob: Job? = null

    init {
        getAllNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onIntent(intent: ViewModelIntent) {
        when (intent) {
            is ViewModelIntent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(intent.note)
                    recentlyDeletedNote = intent.note
                    _uiIntentFlow.emit(UiIntent.DeleteNote("Note Deleted"))
                }
            }

            is ViewModelIntent.Order -> {
                if (state.value.noteOrder::class == intent.noteOrder::class &&
                    state.value.noteOrder.orderType == intent.noteOrder.orderType
                ) {
                    return
                }
                getAllNotes(intent.noteOrder)
            }

            ViewModelIntent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            ViewModelIntent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is ViewModelIntent.GoNoteUpdateScreen -> {
                viewModelScope.launch {
                    _uiIntentFlow.emit(UiIntent.GoNoteEditScreen(intent.noteId, intent.noteColor))
                }

            }
        }
    }

    private fun getAllNotes(noteOrder: NoteOrder) {
        noteJob?.cancel()
        noteJob = noteUseCases.getAllNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}
