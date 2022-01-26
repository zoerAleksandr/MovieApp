package com.example.movieapp.data

import android.os.Parcelable
import com.example.movieapp.data.credits.Credits
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int = 4176,
    var rating: Double = 4.9,
    var genre: String = "жанр",
    var title: String = "название",
    var description: String = "описание",
    var poster: String = "постер url",
    var adult: Boolean = false,
    var credits: List<Credits> = listOf()
) : Parcelable
