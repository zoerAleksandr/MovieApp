package com.example.movieapp

import com.example.movieapp.data.Movie

sealed class AppState {
    data class Success(val movie: Movie) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
