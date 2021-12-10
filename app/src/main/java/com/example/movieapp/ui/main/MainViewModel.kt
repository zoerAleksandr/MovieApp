package com.example.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.AppState
import com.example.movieapp.data.Repository
import com.example.movieapp.data.RepositoryImpl
import kotlin.random.Random

class MainViewModel() : ViewModel() {

    var repo: Repository = RepositoryImpl.newInstance()

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMovie() {

        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1500)
            if (Random.nextInt(2) == 0) {
                liveDataToObserve.postValue(AppState.Error(Exception("Ошибка загрузки")))
            } else {
                liveDataToObserve.postValue(AppState.Success(repo.getMovie()))
            }
        }
    }
}