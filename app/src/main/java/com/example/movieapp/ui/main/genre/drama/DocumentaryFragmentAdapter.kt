package com.example.movieapp.ui.main.genre.drama

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.ui.main.OnItemClick

class DocumentaryFragmentAdapter : RecyclerView.Adapter<DocumentaryFragmentAdapter.DramaViewHolder>() {

    inner class DramaViewHolder(val binding: ItemBinding) :
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
        fun newInstance() = DocumentaryFragmentAdapter()
    }

    private var movies: MutableList<Movie> = mutableListOf()
    var listener: OnItemClick? = null

    fun setMovieNotForAdult(data: List<Movie>) {
        for (i in data.filter { !it.adult }) {
            movies.add(i)
        }
        notifyDataSetChanged()
    }

    fun setDataForAdult(data: MutableList<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DramaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}