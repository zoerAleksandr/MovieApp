package com.example.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.AppState
import com.example.movieapp.data.Movie
import com.example.movieapp.data.Repository
import com.example.movieapp.data.RepositoryImpl
import com.example.movieapp.ui.main.genre.Genre
import kotlin.random.Random

class MainViewModel : ViewModel() {

    var repo: Repository = RepositoryImpl.newInstance()

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovie(genre: Genre) {

        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1500)
            if (Random.nextBoolean()) {
                liveDataToObserve.postValue(AppState.Error(Exception("Ошибка загрузки")))
            } else {
                liveDataToObserve.postValue(
                    AppState.Success(
                        when (genre) {
                            Genre.ACTION -> {
                                repo.getMoviesActionList()
                            }
                            Genre.ANIMATED -> {
                                repo.getMoviesAnimatedList()
                            }
                            Genre.COMEDY -> {
                                repo.getMoviesComedyList()
                            }
                            Genre.DRAMA -> {
                                repo.getMoviesDramaList()
                            }
                            Genre.FAVORITE -> {
                                repo.getMoviesFavoriteList()
                            }
                            Genre.HORROR -> {
                                repo.getMoviesHorrorList()
                            }
                        }
                    )
                )
            }
        }.start()
    }
}