package ru.igorsh.stockview.data.network.model.news

data class NewsResponseItem(
    val id: Int,
    val title: String,
    val description: String,
    val newsLink: String,
    val imageLink: String,
)