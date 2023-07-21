package com.example.noteapp_architecture_sample.feature_note.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp_architecture_sample.feature_note.data.data_source.NoteDatabase
import com.example.noteapp_architecture_sample.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteapp_architecture_sample.feature_note.domain.repository.NoteRepository
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.AddNote
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.DeleteNote
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.GetAllNotes
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.GetNoteById
import com.example.noteapp_architecture_sample.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getAllNotes = GetAllNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNoteById = GetNoteById(repository)
        )
    }
}