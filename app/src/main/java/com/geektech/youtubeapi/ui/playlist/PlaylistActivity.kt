package com.geektech.youtubeapi.ui.playlist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.base.BaseActivity
import com.geektech.youtubeapi.databinding.PlaylistMainBinding
import com.geektech.youtubeapi.ui.video.VideoActivity

class PlaylistActivity : BaseActivity<PlaylistMainBinding, PlaylistViewModel>() {

    override fun inflateViewBinding(): PlaylistMainBinding {
        return PlaylistMainBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = ViewModelProvider(this)[PlaylistViewModel::class.java]

        viewModel.playlists().observe(this){
            val intent = Intent(this, VideoActivity::class.java)
            val id = it.items[0].id
            intent.putExtra("key", id)
            startActivity(intent)
        }
    }
}