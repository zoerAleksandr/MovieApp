package com.example.movieapp.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.movieapp.data.repository.RepositoryImpl
import com.example.movieapp.ui.main.detailFragment.DetailFragment

class DetailReceiver : BroadcastReceiver() {
    companion object {
        const val LOAD_SUCCESS = "LOAD_SUCCESS"
        const val LOAD_FAILED = "LOAD_FAILED"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MyReceiver", "CONNECTIVITY_ACTION")

        when (intent.action) {
            LOAD_SUCCESS -> RepositoryImpl.movieLoaded(intent.extras?.getParcelable(DetailFragment.MOVIE_KEY))
            LOAD_FAILED -> RepositoryImpl.movieLoaded(null)
        }
    }
}