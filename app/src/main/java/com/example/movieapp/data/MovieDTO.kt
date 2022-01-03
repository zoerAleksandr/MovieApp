package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.viewmodel.validationMovie
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class MovieDTO(
    val id: Int?,
    val overview: String?,
    @SerializedName("poster_path")
    val poster: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)

data class ListMovieDTO(
    @SerializedName("created_by")
    val createdBy: String?,
    val description: String?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    val id: Int?,
    val items: List<MovieDTO>?,
    @SerializedName("item_count")
    val itemCount: Int?,
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)

fun getListFromJson(json: String): ListMovieDTO {
    val builder = GsonBuilder()
    val gson = builder.create()
    val listType: Type = object : TypeToken<ListMovieDTO>() {}.type
    return gson.fromJson(json, listType)
}

fun validationActionList(listMovieDTO: ListMovieDTO): MutableList<Movie>{
    val list2 = listMovieDTO.items
    val list: MutableList<Movie> = mutableListOf()
    if (list2 != null) {
        for ((index) in list2.withIndex()){
            list.add(index, validationMovie(listMovieDTO.items[index]))
            Log.e("DEDUGTAG", list[index].toString())
        }
    }
    return list
}
