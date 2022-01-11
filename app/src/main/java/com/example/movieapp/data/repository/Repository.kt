package com.example.movieapp.data.repository

import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import retrofit2.Callback

interface Repository {
    fun getMovie(): Movie
    fun movieLoaded(movie: Movie?)
    fun addListener(listener: OnLoadListener)

    interface OnLoadListener {
        fun onLoaded()
    }

    fun addListenerList(listener: OnLoadListListener)
    fun movieListLoaded(list: ArrayList<Movie>)

    interface OnLoadListListener {
        fun onLoadedList()
    }
}