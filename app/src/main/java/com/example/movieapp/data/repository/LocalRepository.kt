package com.example.movieapp.data.repository

import com.example.movieapp.data.Movie

interface LocalRepository {
    fun getAllHistory(): List<Movie>
    fun saveEntity(movie: Movie)
}