package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.MovieLoader

class DetailViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovie(movieId: Int) {

        liveDataToObserve.value = AppState.Loading

        MovieLoader.load(movieId,
            object : MovieLoader.OnMovieLoadListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    liveDataToObserve.postValue(
                        AppState.Success(
                            validationMovie(movieDTO)
                        )
                    )
                }

                override fun onFailed(exception: Throwable) {
                    liveDataToObserve.postValue(AppState.Error(exception))
                    Log.e("LOG", exception.javaClass.name.toString())
                }
            })
    }

    fun validationMovie(movieDTO: MovieDTO?): Movie {
        val movie = Movie()
        movieDTO?.let {
            movie.title = it.title.toString()
            movie.description = it.overview.toString()
        }
        return movie
    }
}