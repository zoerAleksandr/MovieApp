package com.example.movieapp.ui.main.genre.drama

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentDramaGenreBinding
import com.example.movieapp.ui.main.*
import com.example.movieapp.ui.main.genre.Genre
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MovieListViewModel

class DocumentaryGenreFragment : Fragment(R.layout.fragment_drama_genre) {

    private val binding: FragmentDramaGenreBinding by viewBinding()
    private val adapter = DocumentaryFragmentAdapter.newInstance()

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this)[MovieListViewModel::class.java]
    }
    companion object {
        fun newInstance() = DocumentaryGenreFragment()
        val GENRE = Genre.DOCUMENTARY
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
        adapter.listener = OnItemClick {
            val bundle = Bundle()
            bundle.putParcelable("MOVIE", it)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(bundle))
                .addToBackStack("drama")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesList(GENRE)
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
                        viewModel.getMoviesList(GENRE)
                    })
            }
        }
    }
}