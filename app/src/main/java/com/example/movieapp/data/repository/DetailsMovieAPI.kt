package com.example.movieapp.data.repository

import com.example.movieapp.data.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsMovieAPI {
    @GET("/3/movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<MovieDTO>
}