package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.add_edit_note

sealed class UiIntent{
    data class ShowSnackbar(val message: String): UiIntent()
    object SaveNote: UiIntent()
}
