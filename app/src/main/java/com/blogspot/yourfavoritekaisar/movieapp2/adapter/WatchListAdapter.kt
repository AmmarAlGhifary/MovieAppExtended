package com.blogspot.yourfavoritekaisar.movieapp2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.WatchList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class WatchListAdapter(
    private var item: List<WatchList>,
    private val onItemClick: (item: WatchList) -> Unit
) : RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_watch_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int = item.size

    fun updateItems(items: List<WatchList>) {
        this.item = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_watch_list_poster)

        fun bind(item: WatchList) {
            itemView.setOnClickListener { onItemClick.invoke(item) }
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}