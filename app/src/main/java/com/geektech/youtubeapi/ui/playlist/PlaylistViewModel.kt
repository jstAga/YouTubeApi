package com.geektech.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.utils.App
import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.core.network.result.Resource

class PlaylistViewModel : BaseViewModel() {

     fun getPlaylists(): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylists()
    }
}