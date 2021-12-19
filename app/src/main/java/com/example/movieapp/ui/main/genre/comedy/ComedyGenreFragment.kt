package com.example.movieapp.ui.main.genre.comedy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentComedyGenreBinding

class ComedyGenreFragment : Fragment() {

    private var _binding: FragmentComedyGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = ComedyFragmentAdapter.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComedyGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ComedyGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter.setData(listOf(
            Movie(genre = "Комедия", title = "Всегда говори да"),
            Movie(genre = "Комедия", title = "Лови момент"),
            Movie(genre = "Комедия", title = "Евротур"),
            Movie(genre = "Комедия", title = "За бортом")
        ))
    }
}