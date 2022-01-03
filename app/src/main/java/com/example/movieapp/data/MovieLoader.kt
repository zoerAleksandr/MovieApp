package com.example.movieapp.data

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

object MovieLoader {

    fun loadMovie(movieId: Int, listener: OnMovieLoadListener) {

        Thread.sleep(2000)
        var urlConnection: HttpURLConnection? = null
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/$movieId?api_key=$myApiKey&language=ru-RU")

            urlConnection = uri.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 3000
            urlConnection.connectTimeout = 1000

            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                reader.lines().collect(Collectors.joining("\n"))
            } else {
                ""
            }

            val movieDTO = Gson().fromJson(result, MovieDTO::class.java)

            listener.onLoaded(movieDTO = movieDTO)

        } catch (e: Exception) {
            Log.e("MyLog", e.message.toString())
            listener.onFailed(e)

        } finally {
            urlConnection?.disconnect()
        }
    }

    fun loadList(listId: Int, listener: OnMovieListLoadListener) {

        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())

        Thread {
            Thread.sleep(2000)
            var urlConnection: HttpURLConnection? = null
            try {
                val uri =
                    URL("https://api.themoviedb.org/3/list/$listId?api_key=$myApiKey&language=ru-RU")

                urlConnection = uri.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 5000
                urlConnection.connectTimeout = 2000

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    reader.lines().collect(Collectors.joining("\n"))
                } else {
                    ""
                }

                val listDTO = getListFromJson(result)

                handler.post {
                    listener.onLoaded(listDTO = listDTO)
                }

            } catch (e: Exception) {
                handler.post {
                    Log.e("MyLog1", e.javaClass.name.toString())
                    listener.onFailed(e)
                }
            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }


    interface OnMovieLoadListener {
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(exception: Throwable)
    }

    interface OnMovieListLoadListener {
        fun onLoaded(listDTO: ListMovieDTO)
        fun onFailed(exception: Throwable)
    }
}
