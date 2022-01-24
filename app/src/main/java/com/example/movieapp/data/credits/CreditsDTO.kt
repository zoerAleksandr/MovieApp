package com.example.movieapp.data.credits

import com.google.gson.annotations.SerializedName

data class CreditsDTO(
    var id: Int?,
    var cast: List<ActorDTO>?,
    var crew: List<MemberFilmCrewDTO>?
)

data class ActorDTO(
//    var adult: Boolean?,
//    var gender: Int?,
//    var id: Int?,
//    @SerializedName("known_for_department")
//    var knownForDepartment: String?,
//    var name: String?,
//    @SerializedName("original_name")
//    var originalName: String?,
//    var popularity: Double?,
//    @SerializedName("profile_path")
//    var profilePath: String?,
//    @SerializedName("cast_id")
//    var castId: Int?,
//    var character: String?,
//    @SerializedName("credit_id")
//    var creditId: String?,
//    var order: Int?

    var adult: Boolean?,
    var biography: String?,
    var birthday: String?,
    var deathday: Boolean?,
    var gender: Int?,
    var homepage: Boolean?,
    var id: Int?,
    var imdb_id: String?,
    @SerializedName("known_for_department")
    var knownForDepartment: String?,
    var name: String?,
    @SerializedName("place_of_birth")
    var placeOfBirth: String?,
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?
)

data class MemberFilmCrewDTO(
    var adult: Boolean?,
    var gender: Int?,
    var id: Int?,
    @SerializedName("known_for_department")
    var knownForDepartment: String?,
    var name: String?,
    @SerializedName("original_name")
    var originalName: String?,
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?,
    @SerializedName("credit_id")
    var creditId: String?,
    var department: String?,
    var job: String?
)