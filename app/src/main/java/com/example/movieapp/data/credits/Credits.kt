package com.example.movieapp.data.credits

import android.os.Parcelable
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
    var gender: Int = 2,
    var id: Int = 3223,
    var knownForDepartment: String = "Acting",
    var name: String = "Robert Downey Jr.",
    var originalName: String = "Robert Downey Jr.",
    var popularity: Double = 41.472,
    var profilePath: String = "/5qHNjhtjMD4YWH3UP0rm4tKwxCL.jpg",
    var castId: Int = 46,
    var character: String = "Tony Stark / Iron Man",
    var creditId: String = "52fe4495c3a368484e02b251",
    var order: Int = 0
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