package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.note_list

import androidx.navigation.NavController

sealed class UiIntent{
    data class GoNoteEditScreen(val noteId: Int, val noteColor: Int): UiIntent()
    data class DeleteNote(val message: String): UiIntent()
}
