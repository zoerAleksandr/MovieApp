package com.example.movieapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val overview: String,
    val adult: String,
    val poster: String,
    val timestamp: Long
)
