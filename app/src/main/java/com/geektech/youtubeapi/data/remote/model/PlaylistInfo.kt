package com.geektech.youtubeapi.data.remote.model

data class PlaylistInfo(
    val id : String,
    val title : String,
    val desc : String,
    val itemCount : Int
) : java.io.Serializable