package com.example.movieapp.data.repository

import com.example.movieapp.data.Movie

object RepositoryImpl : Repository {

    private val listeners: MutableList<Repository.OnLoadListener> = mutableListOf()

    private var movie: Movie? = Movie()

    override fun getMovie(): Movie = movie ?: Movie()

    override fun movieLoaded(movie: Movie?) {
        RepositoryImpl.movie = movie
        listeners.forEach { it.onLoaded() }
    }

    override fun addListener(listener: Repository.OnLoadListener) {
        listeners.add(listener)
    }

    private val listenersList: MutableList<Repository.OnLoadListListener> = mutableListOf()

    var movieList: List<Movie> = mutableListOf()

    override fun addListenerList(listener: Repository.OnLoadListListener) {
        listenersList.add(listener)
    }

    override fun movieListLoaded(list: ArrayList<Movie>) {
        movieList = list
        listenersList.forEach { it.onLoadedList() }
    }
}
