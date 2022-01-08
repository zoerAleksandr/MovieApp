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

    fun getMovie(id: String, context: Context) {
        liveDataToObserve.value = AppState.Loading

        context.startService(Intent(context, DetailIntentService::class.java).apply {
            putExtra(DetailFragment.MOVIE_KEY, id)
        })

        RepositoryImpl.addListener(object : Repository.OnLoadListener {
            override fun onLoaded() {
                liveDataToObserve.postValue(AppState.Success(RepositoryImpl.getMovie()))
            }
        })
    }
}