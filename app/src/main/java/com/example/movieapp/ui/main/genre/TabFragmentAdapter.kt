package com.example.movieapp.ui.main.genre

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.ui.main.genre.action.ActionGenreFragment
import com.example.movieapp.ui.main.genre.animated.AnimatedGenreFragment
import com.example.movieapp.ui.main.genre.comedy.ComedyGenreFragment
import com.example.movieapp.ui.main.genre.drama.DramaGenreFragment
import com.example.movieapp.ui.main.genre.favorite.FavoritesListFragment
import com.example.movieapp.ui.main.genre.horror.HorrorGenreFragment

class TabFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 6

    override fun createFragment(position: Int) = when (position) {
        0 -> ComedyGenreFragment.newInstance()
        1 -> ActionGenreFragment.newInstance()
        2 -> AnimatedGenreFragment.newInstance()
        3 -> HorrorGenreFragment.newInstance()
        4 -> DramaGenreFragment.newInstance()
        else -> FavoritesListFragment.newInstance()
    }
}