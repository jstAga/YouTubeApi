package com.geektech.youtubeapi.ui.playlistItem.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.geektech.youtubeapi.data.remote.model.Item
import com.geektech.youtubeapi.databinding.ItemPlaylistItemBinding

class PlaylistItemAdapter(private val onClick: (videoId: String) -> Unit) :
    Adapter<PlaylistItemAdapter.PlaylistItemViewHolder>() {
    private val data = arrayListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Item>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(
            ItemPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistItemViewHolder(private val binding: ItemPlaylistItemBinding) :
        ViewHolder(binding.root) {
        fun bind(model: Item) {
            with(binding) {
                ivImage.load(model.snippet.thumbnails.medium.url)
                tvTitle.text = model.snippet.title
                itemView.setOnClickListener { onClick(model.contentDetails.videoId) }
            }
        }
    }
}