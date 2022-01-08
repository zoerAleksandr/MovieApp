package com.example.movieapp.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.movieapp.viewmodel.MovieListViewModel

class MovieListReceiver : BroadcastReceiver() {
    companion object {
        const val LOAD_SUCCESS = "LOAD_SUCCESS"
        const val LOAD_FAILED = "LOAD_FAILED"
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            LOAD_SUCCESS -> intent.extras?.getParcelableArrayList<Movie>(MovieListViewModel.tag)
                ?.let { RepositoryImpl.movieListLoaded(it) }
            LOAD_FAILED -> RepositoryImpl.movieLoaded(null)
        }
    }
}