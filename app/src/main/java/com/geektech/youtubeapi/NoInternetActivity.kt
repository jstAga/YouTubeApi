package com.geektech.youtubeapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geektech.youtubeapi.databinding.ActivityNoInternetBinding

class NoInternetActivity : AppCompatActivity() {
private lateinit var binding: ActivityNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkConnection()
    }

    private fun checkConnection() {
        val checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                finish()
            }
        }
    }
}