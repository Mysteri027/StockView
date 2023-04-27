package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.domain.model.User
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkInteractor(
    private val networkRepository: NetworkRepository,
) {
    suspend fun getNews(token: String) = networkRepository.getNews(token)

    suspend fun loginUser(user: User) =
        networkRepository.login(AuthRequest(user.email, user.password))

    suspend fun registerUser(user: User) =
        networkRepository.register(RegisterRequest(user.email, user.password))

    suspend fun getStockList(token: String) = networkRepository.getAllStocks(token)

    suspend fun getStockByName(name: String, token: String) =
        networkRepository.getStockByName(name, token)
}