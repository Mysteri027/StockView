package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.domain.model.NewsItem
import ru.igorsh.stockview.domain.model.StockItem
import ru.igorsh.stockview.domain.model.TimelineData
import ru.igorsh.stockview.domain.model.User
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkInteractor(
    private val networkRepository: NetworkRepository,
) {
    suspend fun getNews(token: String): List<NewsItem> {
        return networkRepository.getNews(token)
    }

    suspend fun loginUser(user: User): String {
        return networkRepository.login(AuthRequest(user.email, user.password))
    }

    suspend fun registerUser(user: User): String {
        return networkRepository.register(RegisterRequest(user.email, user.password))
    }

    suspend fun getStockList(token: String): List<StockItem> {
        return networkRepository.getAllStocks(token)
    }

    suspend fun getStockByName(name: String, token: String): StockItem? {
        return networkRepository.getStockByName(name, token)
    }

    suspend fun addToFavorite(name: String, token: String) =
        networkRepository.addToFavorite(name, token)

    suspend fun deleteFromFavorite(name: String, token: String) {
        networkRepository.deleteFromFavorite(name, token)
    }

    suspend fun getTimelineData(ticker: String, startDate: String, endDate: String): TimelineData {
        return networkRepository.getHistoryData(ticker, startDate, endDate)
    }
}