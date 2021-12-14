package com.example.movieapp.ui.main.genre.horror

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentHorrorGenreBinding

class HorrorGenreFragment : Fragment() {

    private var _binding: FragmentHorrorGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = HorrorFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHorrorGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HorrorGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter.setData(
            listOf(
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
        )
    }
}