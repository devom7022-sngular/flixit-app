package com.sngular.flixitApp.ui.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sngular.flixitApp.R
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.util.Constants

class MoviesAdapter(val context: Context, var list: List<MovieBo>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvMovie = itemView.findViewById(R.id.cvMovie) as CardView
        val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
        val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
        val tvDate = itemView.findViewById(R.id.tvDate) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]

        Glide.with(context).load("${Constants.BASE_URL_IMAGE}${movie.posterPath}")
            .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
            .into(holder.ivImage)
        holder.tvTitle.text = movie.title
        holder.tvDate.text = movie.releaseDate
    }

    override fun getItemCount(): Int {
        return list.size
    }
}