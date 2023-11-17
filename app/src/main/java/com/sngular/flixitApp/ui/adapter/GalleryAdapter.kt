package com.sngular.flixitApp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sngular.flixitApp.R
import com.sngular.flixitApp.util.Constants

class GalleryAdapter(
    val context: Context,
    var list: MutableList<String>,
) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvGallery = itemView.findViewById(R.id.cv_gallery) as CardView
        val ivUrl = itemView.findViewById(R.id.iv_url) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = list[position]

        Glide.with(context).load(url)
            .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
            .into(holder.ivUrl)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}