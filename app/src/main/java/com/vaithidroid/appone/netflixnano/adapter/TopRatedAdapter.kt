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
import com.vaithidroid.appone.netflixnano.util.Constants.Companion.SMALL_IMAGE_URL
import kotlinx.android.synthetic.main.item_movies_now_playing.view.*

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    inner class TopRatedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.top_rated_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val movies = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(SMALL_IMAGE_URL + movies.poster_path).into(movie_image)
//            movie_title.text = movies.original_title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}