package ru.igorsh.stockview.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.igorsh.stockview.data.network.model.auth.AuthRequest
import ru.igorsh.stockview.data.network.model.auth.AuthResponse
import ru.igorsh.stockview.data.network.model.auth.RegisterRequest

interface AuthApi {

    @POST("api/v1/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    @POST("api/v1/auth/login")
    suspend fun login(@Body authRequest: AuthRequest): Response<AuthResponse>
}