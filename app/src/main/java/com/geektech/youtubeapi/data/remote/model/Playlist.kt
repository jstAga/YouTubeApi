package com.geektech.youtubeapi.data.remote.model

data class Playlist(
    val items: List<Item>,
)

data class Item(
    val id: String,
    val snippet: Snippet,
    val contentDetails : ContentDetails
)

data class Snippet(
    val channelTitle: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
)

data class Thumbnails(
    val medium: Medium,
)

data class Medium(
    val url: String,
)


data class ContentDetails(
    val itemCount : Int,
    val videoId : String
)


