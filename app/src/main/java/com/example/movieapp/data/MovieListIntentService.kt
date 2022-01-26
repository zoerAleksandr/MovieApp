package com.example.movieapp.data

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.movieapp.viewmodel.MovieListViewModel

class MovieListIntentService : IntentService("MovieListIntentService") {

    override fun onHandleIntent(intent: Intent?) {
//        intent?.getIntExtra(MovieListViewModel.tag, -1)?.let { id ->
//
//            MovieLoader.loadList(id, object : MovieLoader.OnMovieListLoadListener {
//                override fun onLoaded(listDTO: ListMovieDTO) {
//                    applicationContext.sendBroadcast(
//                        Intent(
//                            applicationContext,
//                            MovieListReceiver::class.java
//                        ).apply {
//                            action = MovieListReceiver.LOAD_SUCCESS
//                            putExtra(MovieListViewModel.tag, validationMovieList(listDTO))
//                        }
//                    )
//                }
//
//                override fun onFailed(exception: Throwable) {
//                    Log.d("MovieListIntentService", exception.message.toString())
//                }
//
//            })
//        }
    }
}