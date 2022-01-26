package com.example.movieapp.ui.main.detailFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.credits.Actor
import com.example.movieapp.databinding.ItemActorBinding

class DetailFragmentAdapter: RecyclerView.Adapter<DetailFragmentAdapter.DetailViewHolder>() {
    var listener: OnItemActorClick? = null
    private val actors: MutableList<Actor> = mutableListOf()

    companion object{
        fun newInstance() = DetailFragmentAdapter()
    }

    fun setDataActors(actorList: MutableList<Actor>){
        actors.addAll(actorList)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(private val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(actor: Actor){
                binding.apply {
                    nameActor.text = actor.name
                    posterActor.load("https://image.tmdb.org/t/p/w500/${actor.profilePath}"){
                        crossfade(true)
                        placeholder(R.drawable.placeholder_face)
                    }
                    root.setOnClickListener {
                        listener?.onClick(actor)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ItemActorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount() = actors.size
}

fun interface OnItemActorClick{
    fun onClick(actor: Actor)
}