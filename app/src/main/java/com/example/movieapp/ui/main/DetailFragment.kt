package com.example.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.data.MovieDTO
import com.example.movieapp.data.MovieLoader
import com.example.movieapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Movie>("MOVIE")?.let {
            renderData(it)
        }

    }

    private fun renderData(movie: Movie) {

        MovieLoader.load(movie, object : MovieLoader.OnMovieLoadListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                binding.apply {
                    toolbar.title = movieDTO.title
                    textDescription.text = movieDTO.overview
                }
            }

            override fun onFailed(throwable: Throwable) {
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_LONG).show()
            }
        })
    }
}