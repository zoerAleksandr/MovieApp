package com.example.movieapp.ui.main.genre.animated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentAnimatedGenreBinding

class AnimatedGenreFragment : Fragment() {

    private var _binding: FragmentAnimatedGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = AnimatedFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimatedGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AnimatedGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setData(
            listOf(
                Movie(genre = "Мульт", title = "Моана"),
                Movie(genre = "Мульт", title = "Анастасия"),
                Movie(genre = "Мульт", title = "Геркулес"),
                Movie(genre = "Мульт", title = "В поисках Немо"),
                Movie(genre = "Мульт", title = "Мадагаскар"),
                Movie(genre = "Мульт", title = "Три богатыря")
            )
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}