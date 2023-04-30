package ru.igorsh.stockview.data.repository

import retrofit2.Response
import ru.igorsh.stockview.data.network.AuthApi
import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.data.network.StockApi
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.AuthResponse
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.data.network.model.stock.StockResponse
import ru.igorsh.stockview.domain.model.TimelineData
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val newsApi: NewsApi,
    private val authApi: AuthApi,
    private val stockApi: StockApi,
) : NetworkRepository {

    override suspend fun getNews(token: String) = newsApi.getNews(token)
    override suspend fun login(authRequest: AuthRequest): Response<AuthResponse> =
        authApi.login(authRequest)

    override suspend fun register(registerRequest: RegisterRequest): Response<AuthResponse> =
        authApi.register(registerRequest)

    override suspend fun getAllStocks(token: String): Response<List<StockResponse>> =
        stockApi.getAllStocks(token)

    override suspend fun getStockByName(name: String, token: String): Response<StockResponse> =
        stockApi.getStockByName(name, token)

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
    ): TimelineData? {
        val response = stockApi.getHistoricalData(ticker, startDate, endDate)

        var timelineData: TimelineData? = null

        if (response.isSuccessful) {
            val data = response.body()
            val result = data?.chart?.result?.get(0)
            val time = result?.timestamp

            val date = result?.indicators?.quote!![0].close

            timelineData = TimelineData(time, date)
        }

        return timelineData
    }

}