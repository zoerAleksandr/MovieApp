package com.example.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.ui.main.genre.TabFragmentAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by viewBinding()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val tabFragmentAdapter = TabFragmentAdapter(parentFragmentManager, lifecycle)
        viewPager.adapter = tabFragmentAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Комедия"
                1 -> tab.text = "Боевик"
                2 -> tab.text = "Мульт"
                3 -> tab.text = "Ужасы"
                4 -> tab.text = "Драма"
                5 -> tab.text = "Вестерн"
            }
        }.attach()
    }
}

fun interface OnItemClick {
    fun onClick(movie: Movie)
}

fun setViewStateLoading(
    recyclerView: RecyclerView,
    layout: ShimmerFrameLayout,
    binding: ViewBinding
) {
    binding.apply {
        recyclerView.hide()
        layout.show()
        layout.startShimmer()
    }
}

fun setViewStateSuccess(
    recyclerView: RecyclerView,
    layout: ShimmerFrameLayout,
    binding: ViewBinding
) {
    binding.apply {
        recyclerView.show()
        layout.stopShimmer()
        layout.hide()
    }
}
