package com.example.movieapp.data

interface Repository {
    fun getMovie(): Movie
    fun movieLoaded(movie: Movie?)
    fun addListener(listener: OnLoadListener)

    interface OnLoadListener {
        fun onLoaded()
    }

    fun addListenerList(listener: OnLoadListListener)
    fun movieListLoaded(list: ArrayList<Movie>)

    interface OnLoadListListener{
        fun onLoadedList()
    }
}