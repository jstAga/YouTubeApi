package com.geektech.youtubeapi.data.remote

import com.geektech.youtubeapi.data.remote.model.Playlist
import com.geektech.youtubeapi.data.remote.model.PlaylistItem
import com.geektech.youtubeapi.data.remote.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("channelId") channelId : String,
        @Query("maxResults") maxResults : Int = 20
    ) : Response<Playlist>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("playlistId") channelId : String,
        @Query("maxResults") maxResults : Int
    ) : Response<PlaylistItem>

    @GET("videos")
    suspend fun getVideo(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("id") id : String,
    ) : Response<Video>
}