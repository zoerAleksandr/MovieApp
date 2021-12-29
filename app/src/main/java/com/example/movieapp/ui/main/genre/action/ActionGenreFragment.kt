package com.example.movieapp.ui.main.genre.action

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.MovieLoader
import com.example.movieapp.databinding.FragmentActionGenreBinding
import com.example.movieapp.ui.main.*
import com.example.movieapp.ui.main.genre.Genre
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MainViewModel

class ActionGenreFragment : Fragment(R.layout.fragment_action_genre) {

    private val binding: FragmentActionGenreBinding by viewBinding()
    private val adapter = ActionFragmentAdapter.newInstance()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    companion object {
        fun newInstance() = ActionGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })

        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(context, 3)
        }

        adapter.listener = OnItemClick { movie ->
            val bundle = Bundle().also { it.putParcelable("MOVIE", movie) }

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(bundle))
                .addToBackStack("action")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesList(Genre.ACTION)
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                setViewStateLoading(
                    binding.recyclerView,
                    binding.shimmerLayout,
                    binding
                )
            }
            is AppState.Success<*> -> {
                setViewStateSuccess(
                    binding.recyclerView,
                    binding.shimmerLayout,
                    binding
                )
                @Suppress("UNCHECKED_CAST")
                adapter.setData(appState.data as List<Movie>)
            }
            is AppState.Error -> {
                binding.shimmerLayout.stopShimmer()
                binding.root.showSnackBar(appState.error.message.toString(), "Обновить",
                    {
                        viewModel.getMoviesList(Genre.ACTION)
                    })
            }
        }
    }
}