package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.myApiKey
import com.example.movieapp.ui.main.MainFragment
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val detailsMovieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(DetailsMovieAPI::class.java)

    fun getMovieDetails(id: String, callback: Callback<MovieDTO>) {
        detailsMovieAPI.getMovie(id, myApiKey, MainFragment.selectedLang).enqueue(callback)
    }
}