package com.example.movieapp.data.credits

import com.google.gson.annotations.SerializedName

data class CreditsDTO(
    var id: Int?,
    var cast: List<ActorDTO>?,
    var crew: List<MemberFilmCrewDTO>?
)

data class ActorDTO(
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
    @SerializedName("cast_id")
    var castId: Int?,
    var character: String?,
    @SerializedName("credit_id")
    var creditId: String?,
    var order: Int?
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