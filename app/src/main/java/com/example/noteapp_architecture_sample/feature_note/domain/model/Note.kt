package com.example.noteapp_architecture_sample.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp_architecture_sample.ui.theme.BabyBlue
import com.example.noteapp_architecture_sample.ui.theme.LightGreen
import com.example.noteapp_architecture_sample.ui.theme.RedOrange
import com.example.noteapp_architecture_sample.ui.theme.RedPink
import com.example.noteapp_architecture_sample.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink )
    }
}

class InvalidNoteException( message: String): Exception(message)
