package com.blogspot.yourfavoritekaisar.movieapp2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class TvShowAdapter (private var tvShow : MutableList<TvShow>, private val onTvShowClick : (tvShow : TvShow) -> Unit) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShow[position])
    }

    override fun getItemCount(): Int = tvShow.size

    fun appendTvShows(tvShows: List<TvShow>) {
        this.tvShow.addAll(tvShows)
        notifyItemRangeInserted(this.tvShow.size, tvShows.size - 1)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster : ImageView = itemView.findViewById(R.id.item_tv_show_poster)

        fun bind (tvShow : TvShow) {
            itemView.setOnClickListener { onTvShowClick.invoke(tvShow) }
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tvShow.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }

}