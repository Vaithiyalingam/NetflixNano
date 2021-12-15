package com.vaithidroid.appone.netflixnano.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vaithidroid.appone.netflixnano.R
import com.vaithidroid.appone.netflixnano.models.Result
import com.vaithidroid.appone.netflixnano.util.Constants
import kotlinx.android.synthetic.main.item_movies_now_playing.view.*

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    inner class PopularMoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies_popular,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movies = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(Constants.SMALL_IMAGE_URL + movies.poster_path).into(movie_image)
//            movie_title.text = movies.original_title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}