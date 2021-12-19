package com.example.movieapp.ui.main.genre.animated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.ItemBinding

class AnimatedFragmentAdapter : RecyclerView.Adapter<AnimatedFragmentAdapter.AnimatedViewHolder>() {

    inner class AnimatedViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.rating.text = movie.rating
        }
    }

    companion object{
        fun newInstance() = AnimatedFragmentAdapter()
    }

    private var movies: List<Movie> = listOf()

    fun setData(data: List<Movie>){
        movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimatedViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnimatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimatedViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}