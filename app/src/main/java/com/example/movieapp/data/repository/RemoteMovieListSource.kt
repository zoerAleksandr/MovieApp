package com.example.movieapp.data.repository

import com.example.movieapp.data.ListMovieDTO
import com.example.movieapp.data.myApiKey
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteMovieListSource {
    private val detailsMovieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieListAPI::class.java)

    fun getMovieList(list_id: String, callback: Callback<ListMovieDTO>){
        detailsMovieAPI.getMovieList(list_id, myApiKey).enqueue(callback)
    }
}