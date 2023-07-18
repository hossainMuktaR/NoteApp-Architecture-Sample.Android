package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.data.repository

import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.data.data_source.NoteDao
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.model.Note
import com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}