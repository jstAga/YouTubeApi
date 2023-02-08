package com.geektech.youtubeapi.utils

import com.geektech.youtubeapi.repository.Repository

class App {
    val repository : Repository by lazy {
        Repository()
    }
}