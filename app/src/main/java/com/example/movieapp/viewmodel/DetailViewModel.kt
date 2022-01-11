package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.repository.RemoteDataSource
import com.example.movieapp.data.repository.RepositoryNew
import com.example.movieapp.data.repository.RepositoryNewImpl
import com.example.movieapp.data.validationMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(
    private val repositoryNewImpl: RepositoryNew = RepositoryNewImpl(RemoteDataSource()),
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    fun getData(): LiveData<AppState> = liveDataToObserve

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