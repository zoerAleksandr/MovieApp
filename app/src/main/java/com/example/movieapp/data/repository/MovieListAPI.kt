package com.example.movieapp.data.repository

import com.example.movieapp.data.ListMovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieListAPI {
    @GET("3/list/{list_id}")
    fun getMovieList(
        @Path("list_id") list_id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Call<ListMovieDTO>
}