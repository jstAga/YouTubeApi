package com.geektech.youtubeapi.ui.playlist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.CheckNetworkConnection
import com.geektech.youtubeapi.NoInternetActivity
import com.geektech.youtubeapi.base.BaseActivity
import com.geektech.youtubeapi.databinding.PlaylistMainBinding
import com.geektech.youtubeapi.model.Item
import com.geektech.youtubeapi.ui.playlist.adapter.PlaylistAdapter
import com.geektech.youtubeapi.ui.video.VideoActivity


class PlaylistActivity : BaseActivity<PlaylistMainBinding, PlaylistViewModel>() {
    private var playlistData = listOf<Item>()

    private val adapter by lazy {
        PlaylistAdapter(this::onClick)
    }

    override fun isConnection() {
        super.isConnection()
        val checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                val intent = Intent(this, NoInternetActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun inflateViewBinding(): PlaylistMainBinding {
        return PlaylistMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.rvPlaylist.adapter = adapter
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = ViewModelProvider(this)[PlaylistViewModel::class.java]
        viewModel.playlists().observe(this) {
            playlistData = it.items
            adapter.addData(playlistData)
        }
    }

    private fun onClick(position: Int) {
        val intent = Intent(this, VideoActivity::class.java)
        val id = playlistData[position].id
        intent.putExtra("key", id)
        startActivity(intent)
    }
}