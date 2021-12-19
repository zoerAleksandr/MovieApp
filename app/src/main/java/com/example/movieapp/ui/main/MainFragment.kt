package com.example.movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.ui.main.genre.TabFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                5 -> tab.text = "Избранное"
            }
        }.attach()

    }
}

fun interface OnItemClick{
    fun onClick(movie: Movie)
}