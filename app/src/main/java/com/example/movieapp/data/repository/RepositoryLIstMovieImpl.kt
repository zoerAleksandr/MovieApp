package com.example.movieapp.data.repository

import com.example.movieapp.data.ListMovieDTO
import retrofit2.Callback

class RepositoryLIstMovieImpl(private val remoteMovieListSource: RemoteMovieListSource) :
    RepositoryListMovie {
    override fun getMovieListFromServer(id: String, callback: Callback<ListMovieDTO>) {
        remoteMovieListSource.getMovieList(id, callback)
    }
}