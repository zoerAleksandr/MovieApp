package com.example.movieapp.ui.main.genre.animated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentAnimatedGenreBinding
import com.example.movieapp.ui.main.*
import com.example.movieapp.ui.main.genre.Genre
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class AnimatedGenreFragment : Fragment(R.layout.fragment_animated_genre) {

    private val binding: FragmentAnimatedGenreBinding by viewBinding()
    private val adapter = AnimatedFragmentAdapter.newInstance()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    companion object {
        fun newInstance() = AnimatedGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })

        viewModel.getMovie(Genre.ANIMATED)

        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(context, 3)
        }

        adapter.listener = OnItemClick {
            val bundle = Bundle()
            bundle.putParcelable("MOVIE", it)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(bundle))
                .addToBackStack("animated")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovie(Genre.ANIMATED)
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
            is AppState.Success -> {
                setViewStateSuccess(
                    binding.recyclerView,
                    binding.shimmerLayout,
                    binding
                )
                adapter.setData(appState.movies)
            }
            is AppState.Error -> {
                binding.shimmerLayout.stopShimmer()
                binding.root.showSnackBar(appState.error.message.toString(), "Обновить",
                    {
                        viewModel.getMovie(Genre.ANIMATED)
                    })
            }
        }
    }
}