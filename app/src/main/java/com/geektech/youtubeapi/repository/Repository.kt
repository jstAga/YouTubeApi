package com.geektech.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.data.remote.RemoteDataSource
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import com.geektech.youtubeapi.data.remote.model.Video
import kotlinx.coroutines.Dispatchers

class Repository {
    private val dataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlaylists(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getPlaylists()
        emit(response)
    }

    fun getPlaylistItems(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getPlaylistItems(playlistId, itemCount)
            emit(response)
        }

    fun getVideo(videoId: String): LiveData<Resource<Video>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getVideo(videoId)
        emit(response)
    }
}