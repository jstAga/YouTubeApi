package com.geektech.youtubeapi.model

data class Playlist(
    val items: List<Item>,
)


data class Item(
    val id: String,
    val snippet: Snippet
)

data class Snippet(
    val channelTitle: String,
    val description: String,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
)

data class Thumbnails(
    val high: High,
)

data class High(
    val height: Int,
    val url: String,
    val width: Int
)


