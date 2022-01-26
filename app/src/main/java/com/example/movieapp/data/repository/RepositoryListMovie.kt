package com.example.movieapp.data.repository

import com.example.movieapp.data.ListMovieDTO
import retrofit2.Callback

interface RepositoryListMovie {
    fun getMovieListFromServer(
        id: String,
        callback: Callback<ListMovieDTO>
    )
}