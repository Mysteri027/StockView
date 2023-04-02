package ru.igorsh.stockview.data.network

import retrofit2.Response
import retrofit2.http.GET
import ru.igorsh.stockview.data.model.NewsResponseItem

interface NewsApi {

    @GET("news")
    suspend fun getNews(): Response<List<NewsResponseItem>>
}