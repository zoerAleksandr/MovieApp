package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import retrofit2.Callback

interface RepositoryNew {
    fun getMovieDetailsFromServer(
        id: String,
        callback: Callback<MovieDTO>
    )
}