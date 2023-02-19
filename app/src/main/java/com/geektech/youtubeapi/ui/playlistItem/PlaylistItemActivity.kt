package com.geektech.youtubeapi.ui.playlistItem

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.utils.CheckNetworkConnection
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.data.remote.model.Item
import com.geektech.youtubeapi.data.remote.model.PlaylistInfo
import com.geektech.youtubeapi.databinding.ActivityPlaylistItemBinding
import com.geektech.youtubeapi.ui.playlist.PlaylistActivity.Companion.PLAYLIST_INFO
import com.geektech.youtubeapi.ui.playlistItem.adapter.PlaylistItemAdapter
import com.geektech.youtubeapi.ui.video.VideoActivity

class PlaylistItemActivity : BaseActivity<ActivityPlaylistItemBinding, PlaylistItemViewModel>() {

    private val adapter by lazy { PlaylistItemAdapter(this::onCLick) }

    private val playlistInfo by lazy { intent.getSerializableExtra(PLAYLIST_INFO) as PlaylistInfo }
    private var playlistItemData = listOf<Item>()
    private var videosId = arrayListOf<String>()

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

        binding.rvPlaylistItem.adapter = adapter
        binding.tvTitle.text = playlistInfo.title
        binding.tvDescription.text = playlistInfo.desc
    }

    override fun initListener() {
        super.initListener()
        binding.containerToolbar.tvBack.setOnClickListener { finish() }
    }

    override fun initObserver() {
        super.initObserver()

        getPlaylistItems()
    }

    private fun getVideosId() {
        viewModel.getVideosId(playlistItemData)
        viewModel.liveVideosId.observe(this){
            videosId.addAll(it)
        }
    }

    private fun getPlaylistItems() {
        viewModel.getPlaylistItems(playlistInfo.id, playlistInfo.itemCount).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    playlistItemData = it.data!!.items
                    getVideosId()
                    adapter.addData(playlistItemData)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> Log.e("aga", "LOADING: ")
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[PlaylistItemViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityPlaylistItemBinding {
        return ActivityPlaylistItemBinding.inflate(layoutInflater)
    }

    private fun onCLick(videoId: String) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra(VIDEO_KEY, videoId)
            putExtra(VIDEOS_KEY, videosId)
            startActivity(this)
        }
    }

    companion object {
        const val VIDEO_KEY = "video.key"
        const val VIDEOS_KEY = "videos.key"
    }
}