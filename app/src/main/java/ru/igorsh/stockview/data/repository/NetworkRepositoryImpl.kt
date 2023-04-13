package ru.igorsh.stockview.data.repository

import retrofit2.Response
import ru.igorsh.stockview.data.network.AuthApi
import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.AuthResponse
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val newsApi: NewsApi,
    private val authApi: AuthApi,
) : NetworkRepository {

    override suspend fun getNews(token: String) = newsApi.getNews(token)
    override suspend fun login(authRequest: AuthRequest): Response<AuthResponse> =
        authApi.login(authRequest)

    override suspend fun register(registerRequest: RegisterRequest): Response<AuthResponse> =
        authApi.register(registerRequest)

}