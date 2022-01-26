package com.example.movieapp.data.credits

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(
    var id: Int = 24428,
    var cast: List<Actor>,
    var crew: List<MemberFilmCrew>
) : Parcelable

@Parcelize
data class Actor(
    var adult: Boolean = false,
    var biography: String = "",
    var birthday: String = "",
    var deathday: Boolean = false,
    var gender: Int = 2,
    var homepage: Boolean = false,
    var id: Int = 0,
    var imdbId: String = "",
    var knownForDepartment: String = "",
    var name: String = "",
    var placeOfBirth: String = "",
    var popularity: Double = 0.0,
    var profilePath: String = ""
) : Parcelable

@Parcelize
data class MemberFilmCrew(
    var adult: Boolean = false,
    var gender: Int = 2,
    var id: Int = 36,
    var knownForDepartment: String = "Camera",
    var name: String = "Don Burgess",
    var originalName: String = "Don Burgess",
    var popularity: Double = 6.993,
    var profilePath: String = "/aIB6XjNFDWxRpB2l5GKXWEzMI4O.jpg",
    var creditId: String = "5c2eab380e0a26760339ab79",
    var department: String = "Camera",
    var job: String = "Additional Photography"
) : Parcelable