package com.example.noteapp_architecture_sample.feature_note.presentation.add_edit_note

sealed class UiIntent{
    data class ShowSnackbar(val message: String): UiIntent()
    object SaveNote: UiIntent()
}
