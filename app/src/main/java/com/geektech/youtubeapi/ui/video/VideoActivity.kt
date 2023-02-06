package com.geektech.youtubeapi.ui.video

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.base.BaseActivity
import com.geektech.youtubeapi.databinding.ActivityVideoBinding

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {
    override fun inflateViewBinding(): ActivityVideoBinding {
        return  ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showData()
    }

    private fun showData() {
        Toast.makeText(this, intent.getStringExtra("key"), Toast.LENGTH_SHORT).show()
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
    }
}