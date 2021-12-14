package com.example.movieapp.ui.main.genre.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentFavoritesListBinding

class FavoritesListFragment : Fragment() {

    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!

    private val adapter = FavoriteFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FavoritesListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter.setData(
            listOf(
                Movie(genre = "Ужасы", title = "Сияние"),
                Movie(genre = "Боевик", title = "Механик"),
                Movie(genre = "Мульт", title = "Моана"),
                Movie(genre = "Комедия", title = "Евротур"),
                Movie(genre = "Драма", title = "1+1", rating = "5,0")
            )
        )
    }
}