package com.rojas.androidtestkotlin.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rojas.androidtestkotlin.R
import com.rojas.androidtestkotlin.base.BaseViewHolder
import com.rojas.androidtestkotlin.data.model.Movie

class MainAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(movieList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        override fun bind(item: Movie, position: Int) {
            val imgMovie: ImageView = itemView.findViewById(R.id.img_movie)
            val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
            val txtDescription: TextView = itemView.findViewById(R.id.txt_description)
            Glide.with(context).load("https://image.tmdb.org/t/p/original${item.image}")
                .fitCenter().into(imgMovie)
            txtTitle.text = item.name
            txtDescription.text = item.overview
            itemView.setOnClickListener { itemClickListener.onMovieClick(item) }
        }
    }

}