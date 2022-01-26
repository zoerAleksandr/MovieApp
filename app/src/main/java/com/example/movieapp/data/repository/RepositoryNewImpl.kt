package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.credits.ActorDTO
import com.example.movieapp.data.credits.CreditsDTO
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

class RepositoryActorsImpl(
    private val remoteActorSource: RemoteActorSource
) : RepositoryActors {
    override fun getActorFromServer(id: String, callback: Callback<ActorDTO>) {
        remoteActorSource.getActor(id, callback)
    }
}