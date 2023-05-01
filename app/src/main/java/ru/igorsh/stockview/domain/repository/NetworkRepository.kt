package ru.igorsh.stockview.domain.repository

import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.domain.model.NewsItem
import ru.igorsh.stockview.domain.model.StockItem
import ru.igorsh.stockview.domain.model.TimelineData

interface NetworkRepository {
    suspend fun getNews(token: String): List<NewsItem>

    suspend fun login(authRequest: AuthRequest): String

    suspend fun register(registerRequest: RegisterRequest): String
    suspend fun getAllStocks(token: String): List<StockItem>

    suspend fun getStockByName(name: String, token: String): StockItem?

    suspend fun addToFavorite(name: String, token: String)

    suspend fun deleteFromFavorite(name: String, token: String)

    suspend fun getHistoryData(
        ticker: String,
        startDate: String,
        endDate: String,
    ): TimelineData
}