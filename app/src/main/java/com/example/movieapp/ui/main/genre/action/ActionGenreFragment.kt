package com.example.movieapp.ui.main.genre.action

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentActionGenreBinding

class ActionGenreFragment : Fragment() {

    private var _binding : FragmentActionGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = ActionFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActionGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ActionGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setData(listOf(
            Movie(genre = "Боевик", title = "Терминатор"),
            Movie(genre = "Боевик", title = "Чужой"),
            Movie(genre = "Боевик", title = "Механик"),
            Movie(genre = "Боевик", title = "Форсаж"),

        ))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}