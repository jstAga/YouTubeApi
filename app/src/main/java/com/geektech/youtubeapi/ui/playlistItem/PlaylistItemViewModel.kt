package com.geektech.youtubeapi.ui.playlistItem

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.utils.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.PlaylistItem

class PlaylistItemViewModel : BaseViewModel() {

    fun getPlaylistItems(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> {
        return App().repository.getPlaylistItems(playlistId, itemCount)
    }
}