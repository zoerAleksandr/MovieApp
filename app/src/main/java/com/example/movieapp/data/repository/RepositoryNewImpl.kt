package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import retrofit2.Callback

class RepositoryNewImpl(private val remoteDataSource: RemoteDataSource) : RepositoryNew {
    override fun getMovieDetailsFromServer(id: String, callback: Callback<MovieDTO>) {
        remoteDataSource.getMovieDetails(id, callback)
    }
}