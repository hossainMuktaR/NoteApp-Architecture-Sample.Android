package com.example.noteapp_cleanarch_mvi_mvvm.feature_note.domain.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
