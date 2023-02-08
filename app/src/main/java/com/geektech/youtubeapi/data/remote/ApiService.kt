package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
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

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("playlistId") channelId : String,
        @Query("maxResults") maxResults : Int
    ) : Call<PlaylistItem>
}