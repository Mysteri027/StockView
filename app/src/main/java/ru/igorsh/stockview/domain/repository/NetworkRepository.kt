package ru.igorsh.stockview.domain.repository

import retrofit2.Response
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.AuthResponse
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest
import ru.igorsh.stockview.data.network.model.news.NewsResponseItem

interface NetworkRepository {
    suspend fun getNews(token: String): Response<List<NewsResponseItem>>

    suspend fun login(authRequest: AuthRequest): Response<AuthResponse>

    suspend fun register(registerRequest: RegisterRequest): Response<AuthResponse>
}