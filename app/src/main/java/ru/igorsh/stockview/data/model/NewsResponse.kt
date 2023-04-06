package ru.igorsh.stockview.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("news")
    val news: List<NewsResponseItem>
)