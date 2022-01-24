package com.example.movieapp.data

import android.app.IntentService
import android.content.Intent
import com.example.movieapp.ui.main.detailFragment.DetailFragment

class DetailIntentService : IntentService("DetailIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.getStringExtra(DetailFragment.MOVIE_KEY)?.let { id ->
            MovieLoader.loadMovie(id, object : MovieLoader.OnMovieLoadListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    applicationContext.sendBroadcast(
                        Intent(
                            applicationContext,
                            DetailReceiver::class.java
                        ).apply {
                            action = DetailReceiver.LOAD_SUCCESS
                            putExtra(DetailFragment.MOVIE_KEY, validationMovie(movieDTO))
                        }
                    )
                }

                override fun onFailed(exception: Throwable) {
                    applicationContext.sendBroadcast(
                        Intent(
                            applicationContext,
                            DetailReceiver::class.java
                        ).apply {
                            action = DetailReceiver.LOAD_FAILED
                        }
                    )
                }
            })
        }
    }
}