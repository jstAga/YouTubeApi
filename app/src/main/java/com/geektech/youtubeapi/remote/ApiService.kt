package com.geektech.youtubeapi.remote

import com.geektech.youtubeapi.model.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("channelId") channelId : String,
        @Query("maxResults") maxResults : Int = 20
    ) : Call<Playlist>
}