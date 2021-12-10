package com.example.movieapp.data

interface Repository {
    fun getMovie(): Movie
    fun getMoviesList(): List<Movie>
}