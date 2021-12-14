package com.example.movieapp.data

class RepositoryImpl : Repository {

    companion object {
        fun newInstance(): RepositoryImpl {
            return RepositoryImpl()
        }
    }

    override fun getMovie(): Movie {
        return Movie("id", "5", "Жанр", "Название","Описание", "url постера")
    }

    override fun getMoviesList(): List<Movie> {
        return listOf(
            Movie("id_1", "4,7", "Комедия", "Название_1", "описание_1", "url постера_1"),
            Movie("id_2", "4,3", "Комедия", "Название_2", "описание_2", "url постера_2"),
            Movie("id_3", "4,9", "Комедия", "Название_3", "описание_3", "url постера_3")
        )
    }
}