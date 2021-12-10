package com.example.movieapp.data

class RepositoryImpl : Repository {

    companion object {
        fun newInstance(): RepositoryImpl {
            return RepositoryImpl()
        }
    }

    override fun getMovie(): Movie {
        return Movie("id", "Комедия", "Название", "Описание", "url постера")
    }

    override fun getMoviesList(): List<Movie> {
        return listOf(
            Movie("id_1", "Комедия_1", "Название_1", "Описание_1", "url постера_1"),
            Movie("id_2", "Комедия_2", "Название_2", "Описание_2", "url постера_2"),
            Movie("id_3", "Комедия_3", "Название_3", "Описание_3", "url постера_3"),
        )
    }
}