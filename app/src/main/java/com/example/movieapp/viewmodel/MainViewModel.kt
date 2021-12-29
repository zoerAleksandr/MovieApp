package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.*
import com.example.movieapp.ui.main.genre.Genre

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMoviesList(genre: Genre) {

        liveDataToObserve.value = AppState.Loading

        MovieLoader.loadList(genre.value,
            object : MovieLoader.OnMovieListLoadListener {
                override fun onLoaded(listDTO: ListMovieDTO) {
                    liveDataToObserve.postValue(
                        AppState.Success(validationActionList(listDTO))
                    )
                }

                override fun onFailed(exception: Throwable) {
                    liveDataToObserve.postValue(AppState.Error(exception))
                }

            })
    }
}
