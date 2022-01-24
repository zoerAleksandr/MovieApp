package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.credits.CreditsDTO
import com.example.movieapp.data.credits.RemoteCreditsSource
import com.example.movieapp.data.credits.validationActorsList
import com.example.movieapp.data.repository.*
import com.example.movieapp.data.validationMovie
import com.example.movieapp.ui.main.App.Companion.getHistoryDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(
    private val repositoryNewImpl: RepositoryNew = RepositoryNewImpl(
        RemoteDataSource(),
        RemoteCreditsSource()
    ),
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    val liveDataCreditsToObserve: MutableLiveData<AppState> = MutableLiveData(),
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
        repositoryNewImpl.getMovieDetailsFromServer(id, callbackDetails)
    }

    private val callbackDetails = object : Callback<MovieDTO> {
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            liveDataToObserve.postValue(
                AppState.Success(validationMovie(response.body()))
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }
    }

    fun getCredits(id: String) {
        liveDataCreditsToObserve.value = AppState.Loading
        repositoryNewImpl.getCreditsMovieFromServer(id, callbackCredits)
    }

    private val callbackCredits = object : Callback<CreditsDTO> {
        override fun onResponse(call: Call<CreditsDTO>, response: Response<CreditsDTO>) {
            liveDataCreditsToObserve.postValue(
                AppState.Success(response.body()?.let { validationActorsList(it) })
            )
        }

        override fun onFailure(call: Call<CreditsDTO>, t: Throwable) {
            liveDataCreditsToObserve.postValue(
                AppState.Error(t)
            )
        }

    }

}