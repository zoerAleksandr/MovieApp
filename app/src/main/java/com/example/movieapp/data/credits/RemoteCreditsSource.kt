package com.example.movieapp.data.credits

import com.example.movieapp.data.myApiKey
import com.example.movieapp.data.repository.CreditsMovieAPI
import com.example.movieapp.ui.main.MainFragment
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteCreditsSource {
    private val creditsMovieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(CreditsMovieAPI::class.java)

    fun getCredits(id: String, callback: Callback<CreditsDTO>){
        creditsMovieAPI.getActorsList(id, myApiKey, MainFragment.selectedLang).enqueue(callback)
    }
}