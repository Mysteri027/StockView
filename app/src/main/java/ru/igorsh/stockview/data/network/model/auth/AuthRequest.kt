package ru.igorsh.stockview.data.network.model.auth

data class AuthRequest(
    val email: String,
    val password: String,
)