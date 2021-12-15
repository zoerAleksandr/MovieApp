package com.example.movieapp.ui.main.genre.action

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.AppState
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentActionGenreBinding
import com.example.movieapp.ui.main.MainViewModel
import com.example.movieapp.ui.main.genre.Genre
import com.google.android.material.snackbar.Snackbar

class ActionGenreFragment : Fragment() {

    private var _binding: FragmentActionGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = ActionFragmentAdapter.newInstance()

    private lateinit var viewModel: MainViewModel

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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getData().observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovie(Genre.ACTION)
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                binding.recyclerView.visibility = View.GONE
                binding.shimmerLayout.visibility = View.VISIBLE
                binding.shimmerLayout.startShimmer()
            }
            is AppState.Success -> {
                binding.recyclerView.visibility = View.VISIBLE
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                adapter.setData(
                    listOf(
                        Movie(genre = "Боевик", title = "Терминатор"),
                        Movie(genre = "Боевик", title = "Чужой"),
                        Movie(genre = "Боевик", title = "Механик"),
                        Movie(genre = "Боевик", title = "Форсаж")
                    )
                )
            }
            is AppState.Error -> {
                binding.shimmerLayout.stopShimmer()
                Snackbar.make(
                    binding.root,
                    appState.error.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Обновить") {
                        viewModel.getMovie(Genre.ACTION)
                    }.show()
            }
        }
    }
}