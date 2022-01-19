package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.repository.*
import com.example.movieapp.data.validationMovie
import com.example.movieapp.ui.main.App.Companion.getHistoryDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(
    private val repositoryNewImpl: RepositoryNew = RepositoryNewImpl(RemoteDataSource()),
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDAO())
) : ViewModel() {
    fun getData(): LiveData<AppState> = liveDataToObserve

    fun saveMovie(movie: Movie) {
        Thread {
            historyRepository.saveEntity(movie)
        }.start()
    }

    fun getMovie(id: String) {
        liveDataToObserve.value = AppState.Loading
        repositoryNewImpl.getMovieDetailsFromServer(id, callback)
    }

    private val callback = object : Callback<MovieDTO> {
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            liveDataToObserve.postValue(
                AppState.Success(validationMovie(response.body()))
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }
    }
}