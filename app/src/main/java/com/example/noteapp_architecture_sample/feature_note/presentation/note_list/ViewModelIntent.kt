package com.example.noteapp_architecture_sample.feature_note.presentation.note_list

import com.example.noteapp_architecture_sample.feature_note.domain.model.Note
import com.example.noteapp_architecture_sample.feature_note.domain.utils.NoteOrder

sealed class ViewModelIntent{
    data class Order(val noteOrder: NoteOrder): ViewModelIntent()
    data class DeleteNote(val note: Note): ViewModelIntent()
    object RestoreNote: ViewModelIntent()
    object ToggleOrderSection: ViewModelIntent()
    data class GoNoteUpdateScreen(val noteId: Int, val noteColor: Int): ViewModelIntent()
}
