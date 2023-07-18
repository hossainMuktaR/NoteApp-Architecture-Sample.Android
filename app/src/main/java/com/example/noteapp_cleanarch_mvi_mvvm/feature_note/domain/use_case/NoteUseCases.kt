package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.use_case

data class NoteUseCases(
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)
