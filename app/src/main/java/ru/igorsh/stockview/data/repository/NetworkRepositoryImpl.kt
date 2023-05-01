package ru.igorsh.stockview.data.repository

import retrofit2.Response
import ru.igorsh.stockview.data.mapper.NewsResponseMapper
import ru.igorsh.stockview.data.mapper.StockResponseMapper
import ru.igorsh.stockview.data.network.AuthApi
import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.data.network.StockApi
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.domain.model.NewsItem
import ru.igorsh.stockview.domain.model.StockItem
import ru.igorsh.stockview.domain.model.TimelineData
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val newsApi: NewsApi,
    private val authApi: AuthApi,
    private val stockApi: StockApi,
    private val newsResponseMapper: NewsResponseMapper,
    private val stockResponseMapper: StockResponseMapper
) : NetworkRepository {

    override suspend fun getNews(token: String): List<NewsItem> {
        val response = newsApi.getNews(token)
        if (response.isValid()) {
            val data = response.body() ?: emptyList()
            return data.map {
                newsResponseMapper.map(it)
            }
        }
        return listOf()
    }

    override suspend fun login(authRequest: AuthRequest): String {
        val response = authApi.login(authRequest)
        if (response.isValid()) {
            return response.body()?.token ?: ""
        }
        return ""
    }

    override suspend fun register(registerRequest: RegisterRequest): String {
        val response = authApi.register(registerRequest)
        if (response.isValid()) {
            return response.body()?.token ?: ""
        }
        return ""
    }

    override suspend fun getAllStocks(token: String): List<StockItem> {
        val response = stockApi.getAllStocks(token)
        if (response.isValid()) {
            return response.body()?.map {
                stockResponseMapper.map(it)
            } ?: emptyList()
        }

        return emptyList()
    }

    override suspend fun getStockByName(name: String, token: String): StockItem? {
        val response = stockApi.getStockByName(name, token)
        if (response.isValid()) {
            return stockResponseMapper.map(response.body()!!)
        }
        return null
    }

    override suspend fun addToFavorite(name: String, token: String) {
        stockApi.addToFavorite(name, token)
    }

    override suspend fun deleteFromFavorite(name: String, token: String) {
        stockApi.deleteFromFavorite(name, token)
    }

    override suspend fun getHistoryData(
        ticker: String,
        startDate: String,
        endDate: String
    ): TimelineData {
        val response = stockApi.getHistoricalData(ticker, startDate, endDate)

        if (response.isValid()) {
            val data = response.body()
            val result = data?.chart?.result?.get(0)
            val time = result?.timestamp

            val date = result?.indicators?.quote!![0].close

            return TimelineData(time, date)
        }

        return TimelineData(listOf(), listOf())
    }

    private fun <T> Response<T>.isValid(): Boolean {
        return this.isSuccessful and (this.code() == 200)
    }
}