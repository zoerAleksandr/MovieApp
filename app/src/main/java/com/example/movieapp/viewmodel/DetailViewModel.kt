package com.example.movieapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.DetailIntentService
import com.example.movieapp.data.Repository
import com.example.movieapp.data.RepositoryImpl
import com.example.movieapp.ui.main.DetailFragment

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