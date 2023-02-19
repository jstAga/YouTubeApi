package com.geektech.youtubeapi.data.remote.model

data class Video(
    val etag: String,
    val items: List<Item>,
    val kind: String,
)