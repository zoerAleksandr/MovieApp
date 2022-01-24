package com.example.movieapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.DetailViewModel
import java.net.UnknownHostException

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val MOVIE_KEY = "MOVIE"
        lateinit var movie_id: String
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Movie>(MOVIE_KEY)?.let { movie ->
            movie_id = movie.id.toString()
            viewModel.getMovie(movie_id)
            // здесь дописать получение данных об актерах
            viewModel.getCredits(movie_id)
        }

        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })
        viewModel.liveDataCreditsToObserve.observe(viewLifecycleOwner, { appState ->
            renderDataCredits(appState)
        })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                binding.apply {
                    container.hide()
                    noInternet.hide()
                    shimmerLayout.show()
                    shimmerLayout.startShimmer()
                }
            }
            is AppState.Success<*> -> {
                Log.d("DEBUG", appState.data.toString())
                val movie = appState.data as Movie
                binding.apply {
                    container.show()
                    noInternet.hide()
                    shimmerLayout.hide()
                    shimmerLayout.stopShimmer()

                    toolbar.title = movie.title
                    textDescription.text = movie.description
                    poster.load("https://image.tmdb.org/t/p/w500/${movie.poster}") {
                        crossfade(true)
                        placeholder(R.drawable.background_item)
                    }
                }
                viewModel.saveMovie(movie)
            }
            is AppState.Error -> {
                binding.apply {
                    container.hide()
                    shimmerLayout.hide()
                    shimmerLayout.stopShimmer()
                }

                if (appState.error is UnknownHostException) {
                    binding.noInternet.show()
                    binding.btnReload.setOnClickListener {
                        viewModel.getMovie(movie_id)
                    }
                } else {
                    binding.root.showSnackBar("произошла ошибка", "Обновить", {
                        viewModel.getMovie(movie_id)
                    })
                }

            }
        }
    }

    private fun renderDataCredits(appState: AppState) {
        when (appState) {
            is AppState.Loading -> Toast.makeText(
                requireContext(),
                "Loading Credits",
                Toast.LENGTH_SHORT
            ).show()
            is AppState.Success<*> -> {
                Toast.makeText(
                    requireContext(),
                    "Success Credits ${appState.data}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("Debug", "${appState.data}")
            }
            is AppState.Error -> Toast.makeText(
                requireContext(),
                "Error Credits ${appState.error}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}