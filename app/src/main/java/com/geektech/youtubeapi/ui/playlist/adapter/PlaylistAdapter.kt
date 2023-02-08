package com.geektech.youtubeapi.ui.playlist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.youtubeapi.databinding.ItemPlaylistBinding
import com.geektech.youtubeapi.data.remote.model.Item

class PlaylistAdapter(val onCLick: (position: Int) -> Unit) :
    Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val data = arrayListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Item>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: Item) {
            with(binding) {
                ivImage.load(model.snippet.thumbnails.medium.url)

                if (model.snippet.title.length <= 12){
                    tvPlaylistTitle.text = model.snippet.title
                } else {
                    tvPlaylistTitle.text = model.snippet.title.substring(0,12) + " ..."
                }
                tvTitle.text = model.snippet.title
                tvSeries.text = "${model.contentDetails.itemCount} video series"
            }
            itemView.setOnClickListener {
                onCLick(adapterPosition)
            }
        }
    }

}