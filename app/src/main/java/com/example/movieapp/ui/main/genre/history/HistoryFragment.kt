package com.example.movieapp.ui.main.genre.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.FragmentHistoryBinding
import com.example.movieapp.ui.main.setViewStateLoading
import com.example.movieapp.ui.main.setViewStateSuccess
import com.example.movieapp.ui.main.showSnackBar
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.HistoryViewModel

class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val binding: FragmentHistoryBinding by viewBinding()
    private val adapter = HistoryFragmentAdapter.newInstance()
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.historyLiveData.observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(context, 3)
        }
        viewModel.getAllHistory()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState?) {
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
                adapter.setData(appState.data as MutableList<Movie>)
            }
            is AppState.Error -> {
                binding.shimmerLayout.stopShimmer()
                binding.root.showSnackBar(appState.error.message.toString(), "????????????????",
                    {
                        viewModel.getAllHistory()
                    })
            }
        }
    }
}