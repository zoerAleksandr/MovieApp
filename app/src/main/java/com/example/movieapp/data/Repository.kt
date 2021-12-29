package com.example.movieapp.data

interface Repository {
    fun getMovie(): Movie
    fun getMoviesActionList()
    fun getMoviesAnimatedList(): List<Movie>
    fun getMoviesComedyList(): List<Movie>
    fun getMoviesDramaList(): List<Movie>
    fun getMoviesFavoriteList(): List<Movie>
    fun getMoviesHorrorList(): List<Movie>
}