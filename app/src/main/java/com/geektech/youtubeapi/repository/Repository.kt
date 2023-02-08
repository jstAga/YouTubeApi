package com.geektech.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.data.remote.ApiService
import com.geektech.youtubeapi.core.network.RetrofitClient
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import com.geektech.youtubeapi.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        val data = MutableLiveData<Resource<Playlist>>()

        data.value = Resource.loading()

        apiService.getPlaylists(BuildConfig.API_KEY, Constant.part, Constant.channelId)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    data.value = Resource.error(t.stackTrace.toString(), null, null)
                }
            })
        return data
    }

    fun getPlaylistItems(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> {
        val data = MutableLiveData<Resource<PlaylistItem>>()

        data.value = Resource.loading()

        apiService.getPlaylistItems(BuildConfig.API_KEY, Constant.part, playlistId, itemCount)
            .enqueue(object : Callback<PlaylistItem> {
                override fun onResponse(
                    call: Call<PlaylistItem>,
                    response: Response<PlaylistItem>
                ) {
                    if (response.isSuccessful)
                        data.value = Resource.success(response.body())
                }

                override fun onFailure(call: Call<PlaylistItem>, t: Throwable) {
                    data.value = Resource.error(t.stackTrace.toString(), null, null)
                }
            })
        return data
    }
}