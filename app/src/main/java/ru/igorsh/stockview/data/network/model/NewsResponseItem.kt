package ru.igorsh.stockview.data.network.model

import com.google.gson.annotations.SerializedName

data class NewsResponseItem(
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("news_link") val newsLink: String,
    @SerializedName("title") val title: String
)