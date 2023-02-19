package com.geektech.youtubeapi.ui.playlistItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtubeapi.utils.App
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.Item
import com.geektech.youtubeapi.data.remote.model.PlaylistItem

class PlaylistItemViewModel : BaseViewModel() {
    private val mutableVideosId : MutableLiveData<List<String>> = MutableLiveData()
    val liveVideosId : LiveData<List<String>> = mutableVideosId

    fun getPlaylistItems(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> {
        return App().repository.getPlaylistItems(playlistId, itemCount)
    }

    fun getVideosId(data : List<Item>){
        val result = arrayListOf<String>()
        for (i in data){
            result.add(i.contentDetails.videoId)
        }
        mutableVideosId.value = (result)
    }
}


