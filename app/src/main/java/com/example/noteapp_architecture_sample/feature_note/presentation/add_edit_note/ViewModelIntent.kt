package com.example.noteapp_architecture_sample.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class ViewModelIntent{
    data class EnterTitle(val value: String): ViewModelIntent()
    data class ChangeTitleFocus(val focusState: FocusState): ViewModelIntent()
    data class EnterContent(val value: String): ViewModelIntent()
    data class ChangeContentFocus(val focusState: FocusState): ViewModelIntent()
    data class ChangeColor(val color: Int): ViewModelIntent()
    object SaveNote: ViewModelIntent()
}
