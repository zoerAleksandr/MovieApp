package com.example.movieapp.data.room

import androidx.room.*

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryEntity ORDER BY timestamp DESC")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE (title LIKE :title) ORDER BY timestamp DESC")
    fun getHistoryByTitle(title: String): List<HistoryEntity>

    @Insert(onConflict = 4)
    fun insert(entity: HistoryEntity)
}