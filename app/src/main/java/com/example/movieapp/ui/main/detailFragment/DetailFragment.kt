package com.example.movieapp.ui.main.detailFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.data.credits.Actor
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.ui.main.ActorFragment
import com.example.movieapp.ui.main.hide
import com.example.movieapp.ui.main.show
import com.example.movieapp.ui.main.showSnackBar
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.DetailViewModel
import java.net.UnknownHostException

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private val adapter = DetailFragmentAdapter.newInstance()

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    companion object {
        fun newInstance(bundle: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
        const val ACTOR_KEY = "ACTOR"
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

        binding.recyclerViewActors.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        adapter.listener = OnItemActorClick { actor ->
            val bundle = Bundle().also { it.putParcelable(ACTOR_KEY, actor) }

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ActorFragment.newInstance(bundle))
                .addToBackStack("actor")
                .commit()
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
                @Suppress("UNCHECKED_CAST")
                adapter.setDataActors(appState.data as MutableList<Actor>)
            }
            is AppState.Error -> Toast.makeText(
                requireContext(),
                "Error Credits ${appState.error}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}