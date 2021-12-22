package com.example.movieapp.ui.main.genre.comedy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentComedyGenreBinding
import com.example.movieapp.ui.main.*
import com.example.movieapp.ui.main.genre.Genre
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class ComedyGenreFragment : Fragment(R.layout.fragment_comedy_genre) {

    private val binding: FragmentComedyGenreBinding by viewBinding()
    private val adapter = ComedyFragmentAdapter.newInstance()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    companion object {
        fun newInstance() = ComedyGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })

        viewModel.getMovie(Genre.COMEDY)

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
                .addToBackStack("comedy")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovie(Genre.COMEDY)
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
                        viewModel.getMovie(Genre.COMEDY)
                    })
            }
        }
    }
}