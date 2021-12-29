package com.example.movieapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int = 4176,
    val rating: String = "4,9",
    val genre: String = "жанр",
    var title: String = "название",
    var description: String = "описание",
    val poster: String = "постер url"
) : Parcelable
