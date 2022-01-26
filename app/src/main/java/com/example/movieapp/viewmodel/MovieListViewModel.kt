package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.ListMovieDTO
import com.example.movieapp.data.repository.RemoteMovieListSource
import com.example.movieapp.data.repository.RepositoryLIstMovieImpl
import com.example.movieapp.data.repository.RepositoryListMovie
import com.example.movieapp.data.validationMovieList
import com.example.movieapp.ui.main.genre.Genre
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryListMovie: RepositoryListMovie = RepositoryLIstMovieImpl(
        RemoteMovieListSource()
    )
) : ViewModel() {

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getMoviesList(genre: Genre) {
        liveDataToObserve.value = AppState.Loading
        repositoryListMovie.getMovieListFromServer(genre.value, callback)
    }

    private val callback = object : Callback<ListMovieDTO> {
        override fun onResponse(call: Call<ListMovieDTO>, response: Response<ListMovieDTO>) {
            liveDataToObserve.postValue(
                AppState.Success(response.body()?.let { validationMovieList(it) })
            )
        }

        override fun onFailure(call: Call<ListMovieDTO>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(t))
        }
    }
}
