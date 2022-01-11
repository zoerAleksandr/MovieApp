package com.example.movieapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieListIntentService
import com.example.movieapp.data.repository.Repository
import com.example.movieapp.data.repository.RepositoryImpl
import com.example.movieapp.ui.main.genre.Genre

class MovieListViewModel : ViewModel() {

    companion object {
        lateinit var tag: String
    }

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMoviesList(genre: Genre, context: Context) {

        liveDataToObserve.value = AppState.Loading
        tag = genre.name
        context.startService(Intent(context, MovieListIntentService::class.java).apply {
            putExtra(
                tag,
                Genre.valueOf(tag).value
            )
        })

        RepositoryImpl.addListenerList(object : Repository.OnLoadListListener {
            override fun onLoadedList() {
                liveDataToObserve.postValue(AppState.Success(RepositoryImpl.movieList))
            }
        })
    }
}
