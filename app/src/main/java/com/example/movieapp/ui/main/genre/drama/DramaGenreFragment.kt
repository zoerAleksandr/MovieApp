package com.example.movieapp.ui.main.genre.drama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentDramaGenreBinding

class DramaGenreFragment : Fragment() {

    private var _binding: FragmentDramaGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = DramaFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDramaGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = DramaGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter.setData(listOf(
            Movie(genre = "Драма", title = "1+1", rating = "5,0"),
            Movie(genre = "Драма", title = "Побег из Шоушенка"),
            Movie(genre = "Драма", title = "Зеленая миля"),
            Movie(genre = "Драма", title = "Хатико"),
            Movie(genre = "Драма", title = "Список Шиндлера"),
            Movie(genre = "Драма", title = "Крестный отец"),
            Movie(genre = "Драма", title = "Семь жизней"),
            Movie(genre = "Драма", title = "Запах женщины")
        ))
    }
}