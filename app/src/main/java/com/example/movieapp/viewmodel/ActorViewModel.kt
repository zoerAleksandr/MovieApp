package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.credits.ActorDTO
import com.example.movieapp.data.credits.validationActor
import com.example.movieapp.data.repository.RemoteActorSource
import com.example.movieapp.data.repository.RepositoryActors
import com.example.movieapp.data.repository.RepositoryActorsImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActorViewModel(
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryActor: RepositoryActors = RepositoryActorsImpl(
        RemoteActorSource()
    )
) : ViewModel() {
    fun getActor(id: String) {
        liveDataToObserve.value = AppState.Loading
        repositoryActor.getActorFromServer(id, callback)
    }

    private val callback = object : Callback<ActorDTO> {
        override fun onResponse(call: Call<ActorDTO>, response: Response<ActorDTO>) {
            liveDataToObserve.postValue(
                AppState.Success(validationActor(response.body()))
            )
        }

        override fun onFailure(call: Call<ActorDTO>, t: Throwable) {
            liveDataToObserve.postValue(
                AppState.Error(t)
            )
        }

    }
}