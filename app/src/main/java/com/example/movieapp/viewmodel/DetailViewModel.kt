package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Repository
import com.example.movieapp.data.RepositoryImpl

class DetailViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovie() {
        liveDataToObserve.value = AppState.Loading

        RepositoryImpl.addListener(object : Repository.OnLoadListener {
            override fun onLoaded() {
                liveDataToObserve.postValue(AppState.Success(RepositoryImpl.getMovie()))
            }
        })
    }
}