package com.example.movieapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.DetailViewModel
import java.net.UnknownHostException
import kotlin.properties.Delegates

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private var movieID by Delegates.notNull<Int>()

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Movie>("MOVIE")?.let { movie ->

            movieID = movie.id

            Log.e("LOG", movieID.toString())
            viewModel.getData().observe(viewLifecycleOwner, { appState ->
                renderData(appState)
            })

            viewModel.getMovie(movieID)
        }

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
                val movie = appState.data as Movie
                binding.apply {
                    container.show()
                    noInternet.hide()
                    shimmerLayout.hide()
                    shimmerLayout.stopShimmer()

                    toolbar.title = movie.title
                    textDescription.text = movie.description
                }
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
                        viewModel.getMovie(movieID)
                    }
                } else {
                    binding.root.showSnackBar("произошла ошибка", "Обновить", {
                        viewModel.getMovie(movieID)
                    })
                }

            }
        }
    }
}