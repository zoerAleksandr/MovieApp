package com.example.movieapp.data

import android.app.IntentService
import android.content.Intent
import com.example.movieapp.ui.main.DetailFragment

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<Movie>(DetailFragment.MOVIE_KEY)?.let { movie ->
            MovieLoader.loadMovie(movie.id, object : MovieLoader.OnMovieLoadListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    applicationContext.sendBroadcast(
                        Intent(
                            applicationContext,
                            MyReceiver::class.java
                        ).apply {
                            action = MyReceiver.LOAD_SUCCESS
                            putExtra(DetailFragment.MOVIE_KEY, validationMovie(movieDTO))
                        }
                    )
                }

                override fun onFailed(exception: Throwable) {
                    applicationContext.sendBroadcast(
                        Intent(
                            applicationContext,
                            MyReceiver::class.java
                        ).apply {
                            action = MyReceiver.LOAD_FAILED
                        }
                    )
                }
            })
        }
    }
}