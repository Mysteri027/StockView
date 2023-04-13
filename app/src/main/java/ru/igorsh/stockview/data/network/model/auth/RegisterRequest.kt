package ru.igorsh.stockview.data.network.model.auth

data class RegisterRequest(
    val email: String,
    val password: String,
)