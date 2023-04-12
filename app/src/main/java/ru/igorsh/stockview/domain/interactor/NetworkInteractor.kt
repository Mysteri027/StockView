package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.domain.model.User
import ru.igorsh.stockview.domain.repository.NetworkRepository
import ru.igorsh.stockview.domain.repository.UserRepository

class NetworkInteractor(
    private val userRepository: UserRepository,
    private val networkRepository: NetworkRepository,
) {
    suspend fun getNews() = networkRepository.getNews()

    fun loginUser(user: User) = userRepository.logInUser(user)

    fun logOutUser() = userRepository.logOutUser()

    fun registerUser(user: User) = userRepository.registerUser(user)
}