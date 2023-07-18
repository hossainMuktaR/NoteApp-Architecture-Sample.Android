package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.note_list

import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils.NoteOrder

sealed class NoteListEvent{
    data class Order(val noteOrder: NoteOrder): NoteListEvent()
    data class DeleteNote(val note: Note): NoteListEvent()
    object RestoreNote: NoteListEvent()
    object ToggleOrderSection: NoteListEvent()
}
