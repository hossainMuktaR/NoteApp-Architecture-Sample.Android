package com.example.noteapp_architecture_sample.feature_note.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
