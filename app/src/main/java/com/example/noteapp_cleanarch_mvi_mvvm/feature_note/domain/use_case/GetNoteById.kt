package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.use_case

import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}