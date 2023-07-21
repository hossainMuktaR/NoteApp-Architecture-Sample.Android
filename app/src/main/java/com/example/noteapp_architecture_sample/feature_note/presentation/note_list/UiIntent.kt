package com.example.noteapp_architecture_sample.feature_note.presentation.note_list

sealed class UiIntent{
    data class GoNoteEditScreen(val noteId: Int, val noteColor: Int): UiIntent()
    data class DeleteNote(val message: String): UiIntent()
}
