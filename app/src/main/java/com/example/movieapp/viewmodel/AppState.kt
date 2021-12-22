package com.example.movieapp.viewmodel

import com.example.movieapp.data.Movie

sealed class AppState {
    data class Success(val movies: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
