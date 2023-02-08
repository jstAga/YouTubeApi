package com.geektech.youtubeapi.ui.playlist

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.utils.CheckNetworkConnection
import com.geektech.youtubeapi.core.network.result.Status.*
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.databinding.PlaylistMainBinding
import com.geektech.youtubeapi.data.remote.model.Item
import com.geektech.youtubeapi.data.remote.model.PlaylistInfo
import com.geektech.youtubeapi.ui.playlist.adapter.PlaylistAdapter
import com.geektech.youtubeapi.ui.playlistItem.PlaylistItemActivity


class PlaylistActivity : BaseActivity<PlaylistMainBinding, PlaylistViewModel>() {
    private val adapter by lazy { PlaylistAdapter(this::onClick) }
    private var playlistData = listOf<Item>()

    override fun isConnection() {
        super.isConnection()
        CheckNetworkConnection(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.containerNoInternet.visibility = View.GONE
                binding.containerContent.visibility = View.VISIBLE
            } else {
                binding.containerNoInternet.visibility = View.VISIBLE
                binding.containerContent.visibility = View.GONE
            }
        }
    }

    override fun initViews() {
        super.initViews()
        binding.rvPlaylist.adapter = adapter
    }

    override fun initObserver() {
        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                SUCCESS -> {
                    playlistData = it.data!!.items
                    adapter.addData(playlistData)
                }
                ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                LOADING -> Log.e("aga", "LOADING: ")
            }
        }
    }

    private fun onClick(position: Int) {
        val intent = Intent(this, PlaylistItemActivity::class.java)
        intent.putExtra(
            PLAYLIST_INFO,
            PlaylistInfo(
                playlistData[position].id,
                playlistData[position].snippet.title,
                playlistData[position].snippet.description,
                playlistData[position].contentDetails.itemCount
            )
        )
        startActivity(intent)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(): PlaylistMainBinding {
        return PlaylistMainBinding.inflate(layoutInflater)
    }

    companion object {
        const val PLAYLIST_INFO = "playlist.info"
    }
}