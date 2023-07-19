package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.presentation.util

sealed class Screen(val route: String){
    object NoteListScreen: Screen("note_list_Screen")
    object AddEditNoteScreen: Screen("add_edit_note_Screen")
}
