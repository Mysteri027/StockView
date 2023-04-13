package ru.igorsh.stockview.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import ru.igorsh.stockview.data.network.model.news.NewsResponseItem

interface NewsApi {
    @GET("/api/v1/news")
    suspend fun getNews(@Header("Authorization") token: String): Response<List<NewsResponseItem>>
}