package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.repository.LocalRepository
import com.example.movieapp.data.repository.LocalRepositoryImpl
import com.example.movieapp.ui.main.App.Companion.getHistoryDAO

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDAO())
) : ViewModel() {

    fun getAllHistory(){
        historyLiveData.value = AppState.Loading
        Thread{
            Thread.sleep(1000)
            historyLiveData.postValue(
                AppState.Success(historyRepository.getAllHistory())
            )
        }.start()
    }
}