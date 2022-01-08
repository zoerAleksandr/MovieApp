package com.example.movieapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int = 4176,
    var rating: Double = 4.9,
    var genre: String = "жанр",
    var title: String = "название",
    var description: String = "описание",
    var poster: String = "постер url"
) : Parcelable
