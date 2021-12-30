package com.example.movieapp.data

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val id: Int?,
    val overview: String?,
    @SerializedName("poster_path")
    val poster: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)
