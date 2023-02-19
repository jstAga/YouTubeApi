package com.geektech.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.geektech.youtubeapi.core.network.result.Resource
import com.geektech.youtubeapi.core.ui.BaseViewModel
import com.geektech.youtubeapi.data.remote.model.Video
import com.geektech.youtubeapi.utils.App

class VideoViewModel : BaseViewModel() {

    fun getVideo(videId:String): LiveData<Resource<Video>> {
        return App().repository.getVideo(videId)
    }
}