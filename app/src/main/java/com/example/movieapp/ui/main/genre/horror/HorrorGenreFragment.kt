package com.example.movieapp.ui.main.genre.horror

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHorrorGenreBinding
import com.example.movieapp.ui.main.DetailFragment
import com.example.movieapp.ui.main.OnItemClick
import com.example.movieapp.ui.main.genre.Genre
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class HorrorGenreFragment : Fragment() {

    private var _binding: FragmentHorrorGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter = HorrorFragmentAdapter.newInstance()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHorrorGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HorrorGenreFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getData().observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })

        viewModel.getMovie(Genre.HORROR)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        adapter.listener = OnItemClick {

            val bundle = Bundle()
            bundle.putParcelable("MOVIE", it)

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(bundle))
                .addToBackStack("")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovie(Genre.HORROR)
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
                adapter.setData(appState.movies)
            }
            is AppState.Error -> {
                binding.shimmerLayout.stopShimmer()
                Snackbar.make(
                    binding.root,
                    appState.error.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Обновить") {
                        viewModel.getMovie(Genre.HORROR)
                    }.show()
            }
        }
    }
}