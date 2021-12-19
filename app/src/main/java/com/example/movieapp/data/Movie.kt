package com.example.movieapp.data

data class Movie(
    val id: String = "1",
    val rating: String = "4,9",
    val genre: String,
    val title: String,
    val description: String = "описание",
    val poster: String = "постер url"
)
