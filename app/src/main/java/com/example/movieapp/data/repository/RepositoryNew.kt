package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.credits.ActorDTO
import com.example.movieapp.data.credits.CreditsDTO
import retrofit2.Callback

interface RepositoryNew {
    fun getMovieDetailsFromServer(
        id: String,
        callback: Callback<MovieDTO>
    )

    fun getCreditsMovieFromServer(
        id: String,
        callback: Callback<CreditsDTO>
    )
}

interface RepositoryActors {
    fun getActorFromServer(
        id: String,
        callback: Callback<ActorDTO>
    )
}