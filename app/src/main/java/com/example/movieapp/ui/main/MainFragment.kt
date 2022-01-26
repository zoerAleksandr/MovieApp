package com.example.movieapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.get
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
        const val IS_ADULT_KEY = "ADULT_KEY"
        const val LANGUAGE = "LANGUAGE"
        lateinit var selectedLang: String
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adultState = requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getBoolean(IS_ADULT_KEY, false)

        var languageState = requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getBoolean(LANGUAGE, false)

        selectedLang = when {
            (languageState) -> LangEnum.ENG.lang
            else -> LangEnum.RU.lang
        }

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
                6 -> tab.text = "История"
            }
        }.attach()

        fun saveAdult() {
            adultState = !adultState
            activity?.let {
                with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                    putBoolean(IS_ADULT_KEY, adultState)
                    apply()
                }
            }
        }

        val switchAdult = binding.navigationView.menu[0].actionView as SwitchCompat
        switchAdult.isChecked = adultState
        switchAdult.setOnCheckedChangeListener { _, _ ->
            saveAdult()
        }

        fun saveLang() {
            languageState = !languageState
            activity?.let {
                with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                    putBoolean(LANGUAGE, languageState)
                    apply()
                }
            }
        }

        val switchLanguage = binding.navigationView.menu[1].actionView as SwitchCompat
        switchLanguage.isChecked = languageState
        switchLanguage.text = when {
            (languageState) -> "ENG"
            else -> "RU"
        }

        switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchLanguage.text = "ENG"
                selectedLang = LangEnum.ENG.lang
            } else {
                switchLanguage.text = "RU"
                selectedLang = LangEnum.RU.lang
            }
            saveLang()

        }

        binding.btnMenu.setOnClickListener {
            binding.main.open()
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.contacts -> {
                    startActivity(Intent(context, ContactsActivity::class.java))
                    binding.main.close()
                    true
                }
                else -> false
            }
        }

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
