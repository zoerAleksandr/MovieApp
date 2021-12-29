package com.example.movieapp.data

import android.os.Looper
import android.util.Log
import com.example.movieapp.viewmodel.validationMovie

class RepositoryImpl : Repository {

    companion object {
        fun newInstance() = RepositoryImpl()
    }

    override fun getMovie(): Movie {
        return Movie(1, "5", "Жанр", "Название", "Описание", "url постера")
    }

    override fun getMoviesActionList(){

    }

    override fun getMoviesAnimatedList() = listOf(
        Movie(genre = "Мульт", title = "Моана"),
        Movie(genre = "Мульт", title = "Анастасия"),
        Movie(genre = "Мульт", title = "Геркулес"),
        Movie(genre = "Мульт", title = "В поисках Немо"),
        Movie(genre = "Мульт", title = "Мадагаскар"),
        Movie(genre = "Мульт", title = "Три богатыря")
    )


    override fun getMoviesComedyList() = listOf(
        Movie(genre = "Комедия", title = "Всегда говори да"),
        Movie(genre = "Комедия", title = "Лови момент"),
        Movie(genre = "Комедия", title = "Евротур"),
        Movie(genre = "Комедия", title = "За бортом")
    )


    override fun getMoviesDramaList() = listOf(
        Movie(genre = "Драма", title = "1+1", rating = "5,0"),
        Movie(genre = "Драма", title = "Побег из Шоушенка"),
        Movie(genre = "Драма", title = "Зеленая миля"),
        Movie(genre = "Драма", title = "Хатико"),
        Movie(genre = "Драма", title = "Список Шиндлера"),
        Movie(genre = "Драма", title = "Крестный отец"),
        Movie(genre = "Драма", title = "Семь жизней"),
        Movie(genre = "Драма", title = "Запах женщины")
    )


    override fun getMoviesFavoriteList() = listOf(
        Movie(genre = "Ужасы", title = "Сияние"),
        Movie(genre = "Боевик", title = "Механик"),
        Movie(genre = "Мульт", title = "Моана"),
        Movie(genre = "Комедия", title = "Евротур"),
        Movie(genre = "Драма", title = "1+1", rating = "5,0")
    )


    override fun getMoviesHorrorList() = listOf(
        Movie(genre = "Ужасы", title = "Изгоняющий дьявола", rating = "5,0"),
        Movie(genre = "Ужасы", title = "Сияние"),
        Movie(genre = "Ужасы", title = "Кошмар на улице Вязов"),
        Movie(genre = "Ужасы", title = "Вой"),
        Movie(genre = "Ужасы", title = "Зловещие мертвецы"),
        Movie(genre = "Ужасы", title = "Челюсти"),
        Movie(genre = "Ужасы", title = "Молчание ягнят"),
        Movie(genre = "Ужасы", title = "Вой"),
        Movie(genre = "Ужасы", title = "Зловещие мертвецы"),
        Movie(genre = "Ужасы", title = "Челюсти"),
        Movie(genre = "Ужасы", title = "Молчание ягнят"),
        Movie(genre = "Ужасы", title = "Вой"),
        Movie(genre = "Ужасы", title = "Зловещие мертвецы"),
        Movie(genre = "Ужасы", title = "Челюсти"),
        Movie(genre = "Ужасы", title = "Молчание ягнят"),
        Movie(genre = "Ужасы", title = "Звонок")
    )
}
