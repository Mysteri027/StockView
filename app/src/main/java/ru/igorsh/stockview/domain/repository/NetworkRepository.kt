package ru.igorsh.stockview.domain.repository

import retrofit2.Response
import ru.igorsh.stockview.data.model.NewsResponseItem

interface NetworkRepository {
    suspend fun getNews(): Response<List<NewsResponseItem>>
}