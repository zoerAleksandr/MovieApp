package com.example.movieapp.ui.main.genre.comedy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.ui.main.OnItemClick

class ComedyFragmentAdapter : RecyclerView.Adapter<ComedyFragmentAdapter.ComedyViewHolder>() {

    inner class ComedyViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                title.text = movie.title
                rating.text = movie.rating.toString()
                poster.load("https://image.tmdb.org/t/p/w500/${movie.poster}"){
                    crossfade(true)
                    placeholder(R.drawable.background_item)
                }
                root.setOnClickListener {
                    listener?.onClick(movie)
                }
            }
        }
    }

    companion object {
        fun newInstance() = ComedyFragmentAdapter()
    }

    private var movies: List<Movie> = listOf()
    var listener: OnItemClick? = null

    fun setData(data: List<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComedyViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComedyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComedyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}