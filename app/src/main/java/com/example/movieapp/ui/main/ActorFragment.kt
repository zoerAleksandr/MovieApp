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
import com.example.movieapp.data.credits.Actor
import com.example.movieapp.databinding.FragmentActorBinding
import com.example.movieapp.ui.main.detailFragment.DetailFragment
import com.example.movieapp.viewmodel.ActorViewModel
import com.example.movieapp.viewmodel.AppState

class ActorFragment : Fragment(R.layout.fragment_actor) {

    companion object {
        fun newInstance(bundle: Bundle?) = ActorFragment().also {
            it.arguments = bundle
        }
        const val LOCATION = "LOCATION"
    }

    private lateinit var actorId: String
    lateinit var placeOfBirth: String

    private val viewModel: ActorViewModel by lazy {
        ViewModelProvider(this)[ActorViewModel::class.java]
    }

    private val binding: FragmentActorBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Actor>(DetailFragment.ACTOR_KEY)?.let { actor ->
            actorId = actor.id.toString()
            viewModel.getActor(actorId)
        }
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
            renderData(appState)
        })
        binding.locationBtn.setOnClickListener {
            val bundle = Bundle().also { it.putString(LOCATION, placeOfBirth) }
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MapsFragment.newInstance(bundle))
                .addToBackStack("maps")
                .commit()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success<*> -> {
                Log.d("debug", "AppState.Success ${appState.data}")
                val actor = appState.data as Actor
                binding.apply {
                    poster.load("https://image.tmdb.org/t/p/w500/${actor.profilePath}")
                    name.text = actor.name
                    location.text = actor.placeOfBirth
                    biography.text = actor.biography
                }
                placeOfBirth = actor.placeOfBirth
            }
            is AppState.Error -> {
                Toast.makeText(context, "Error ${appState.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}