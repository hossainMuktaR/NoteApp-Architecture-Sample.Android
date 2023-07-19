package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.use_case

import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note

data class NoteUseCases(
    val getAllNotes: GetAllNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNoteById: GetNoteById,
)
