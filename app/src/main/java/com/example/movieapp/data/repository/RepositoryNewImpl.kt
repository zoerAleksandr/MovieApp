package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.credits.CreditsDTO
import com.example.movieapp.data.credits.RemoteCreditsSource
import retrofit2.Callback

class RepositoryNewImpl(
    private val remoteDataSource: RemoteDataSource,
    private val remoteCreditsSource: RemoteCreditsSource
) : RepositoryNew {
    override fun getMovieDetailsFromServer(id: String, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(id, callback)
    }

    override fun getCreditsMovieFromServer(id: String, callback: Callback<CreditsDTO>) {
        remoteCreditsSource.getCredits(id, callback)
    }
}