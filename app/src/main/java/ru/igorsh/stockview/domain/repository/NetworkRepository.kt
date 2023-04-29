package ru.igorsh.stockview.domain.repository

import retrofit2.Response
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.AuthResponse
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.data.network.model.news.NewsResponseItem
import ru.igorsh.stockview.data.network.model.stock.StockResponse

interface NetworkRepository {
    suspend fun getNews(token: String): Response<List<NewsResponseItem>>

    suspend fun login(authRequest: AuthRequest): Response<AuthResponse>

    suspend fun register(registerRequest: RegisterRequest): Response<AuthResponse>

    suspend fun getAllStocks(token: String): Response<List<StockResponse>>

    suspend fun getStockByName(name: String, token: String): Response<StockResponse>

    suspend fun addToFavorite(name: String, token: String)

    suspend fun deleteFromFavorite(name: String, token: String)
}