package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.note_list

import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils.NoteOrder
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false
)
