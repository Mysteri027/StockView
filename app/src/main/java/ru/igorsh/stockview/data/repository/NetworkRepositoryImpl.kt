package ru.igorsh.stockview.data.repository

import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val newsApi: NewsApi
) : NetworkRepository {

    override suspend fun getNews() = newsApi.getNews()
}