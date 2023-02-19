package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.BuildConfig
import com.geektech.youtubeapi.core.network.BaseDataSource
import com.geektech.youtubeapi.core.network.RetrofitClient
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import com.geektech.youtubeapi.data.remote.model.Video
import com.geektech.youtubeapi.utils.Constant

class RemoteDataSource : BaseDataSource() {

    private val apiService: ApiService by lazy { RetrofitClient.create() }

    suspend fun getPlaylists(): Resource<Playlist> {
        return getResult { apiService.getPlaylists(BuildConfig.API_KEY, Constant.part, Constant.channelId) }
    }

    suspend fun getPlaylistItems(playlistId: String, itemCount: Int): Resource<PlaylistItem> {
        return getResult {
            apiService.getPlaylistItems(BuildConfig.API_KEY,
                Constant.part,
                playlistId,
                itemCount)
        }
    }

    suspend fun getVideo(videoId: String): Resource<Video> {
        return getResult {
            apiService.getVideo(BuildConfig.API_KEY,
                Constant.part, videoId)
        }
    }
}