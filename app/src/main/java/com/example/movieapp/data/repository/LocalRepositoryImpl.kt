package com.example.movieapp.data.repository

import com.example.movieapp.data.Movie
import com.example.movieapp.data.room.HistoryDAO
import com.example.movieapp.data.room.HistoryEntity
import java.util.*

class LocalRepositoryImpl(private val localDataSource: HistoryDAO) : LocalRepository {
    override fun getAllHistory(): List<Movie> {
        return localDataSource.all()
            .map { historyEntity ->
                Movie(
                    id = historyEntity.id.toInt(),
                    title = historyEntity.title,
                    description = historyEntity.overview,
                    adult = historyEntity.adult.toBoolean(),
                    poster = historyEntity.poster
                )
            }
    }

    override fun saveEntity(movie: Movie) {
        localDataSource.insert(
            HistoryEntity(
                id = 0,
                title = movie.title,
                overview = movie.description,
                adult = movie.adult.toString(),
                poster = movie.poster,
                timestamp = Date().time
            )
        )
    }
}