package com.geektech.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.`object`.Constant
import com.geektech.youtubeapi.model.Playlist
import com.geektech.youtubeapi.base.BaseViewModel
import com.geektech.youtubeapi.remote.ApiService
import com.geektech.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlist> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlist> {

        val data = MutableLiveData<Playlist>()

        apiService.getPlaylists(BuildConfig.API_KEY, Constant.part, Constant.channelId)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return data
    }
}