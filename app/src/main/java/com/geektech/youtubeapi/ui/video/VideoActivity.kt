package com.geektech.youtubeapi.ui.video

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtubeapi.CheckNetworkConnection
import com.geektech.youtubeapi.NoInternetActivity
import com.geektech.youtubeapi.base.BaseActivity
import com.geektech.youtubeapi.databinding.ActivityVideoBinding

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>() {

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