package com.geektech.youtubeapi.ui.video

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geektech.youtubeapi.core.network.result.Status
import com.geektech.youtubeapi.core.ui.BaseActivity
import com.geektech.youtubeapi.data.remote.model.Item
import com.geektech.youtubeapi.databinding.ActivityVideoBinding
import com.geektech.youtubeapi.ui.playlistItem.PlaylistItemActivity.Companion.VIDEOS_KEY
import com.geektech.youtubeapi.ui.playlistItem.PlaylistItemActivity.Companion.VIDEO_KEY
import com.geektech.youtubeapi.utils.CheckNetworkConnection

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

    private lateinit var videoId: String
    private lateinit var videosId: List<String>

    override fun inflateViewBinding(): ActivityVideoBinding = ActivityVideoBinding.inflate(layoutInflater)
    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videosId = intent.getStringArrayListExtra(VIDEOS_KEY)!!
    }

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

    override fun initListener() {
        super.initListener()
        binding.containerToolbar.tvBack.setOnClickListener { finish() }

        binding.ivNext.setOnClickListener {
            if (videosId.indexOf(videoId) != videosId.size - 1) {
                videoId = videosId[videosId.indexOf(videoId) + 1]
                loadVideoData(videoId)
            }
        }

        binding.ivPrevious.setOnClickListener {
            if (videosId.indexOf(videoId) != 0) {
                videoId = videosId[videosId.indexOf(videoId) - 1]
                loadVideoData(videoId)
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        getVideo()
    }

    private fun getVideo() {
        videoId = intent.getStringExtra(VIDEO_KEY)!!
        loadVideoData(videoId)
    }

    private fun loadVideoData(videoId: String) {
        viewModel.getVideo(videoId).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    setData(it.data!!.items[0])
                    checkButtonsVisibility()
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> Log.e("aga", "LOADING: ")
            }
        }

    }

    private fun setData(model: Item) {
        with(binding) {
            tvTitle.text = model.snippet.title
            tvDescription.text = model.snippet.description
            ivImage.load(model.snippet.thumbnails.medium.url)
        }
    }

    private fun checkButtonsVisibility(){
        if (videosId.indexOf(videoId) == videosId.size -1){
            binding.ivNext.visibility = View.GONE
        } else {
            binding.ivNext.visibility = View.VISIBLE
        }
        if (videosId.indexOf(videoId) == 0){
            binding.ivPrevious.visibility = View.GONE
        } else {
            binding.ivPrevious.visibility = View.VISIBLE
        }
    }
}