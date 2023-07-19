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

    private var recentlyDeletedNote: Note? = null
    private var noteJob: Job? = null

    init {
        getAllNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteUseCases.deleteNote(note)
            recentlyDeletedNote = note
        }
    }

    fun toggleOrderSection(){
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    fun restoreNote(){
        viewModelScope.launch {
            noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
            recentlyDeletedNote = null
        }
    }
    fun orderNoteList(noteOrder: NoteOrder){
        if (state.value.noteOrder::class == noteOrder::class &&
            state.value.noteOrder.orderType == noteOrder.orderType
        ) {
            return
        }
        getAllNotes(noteOrder)
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
