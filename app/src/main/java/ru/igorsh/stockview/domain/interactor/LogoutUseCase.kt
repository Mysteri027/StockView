package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.domain.repository.UserRepository

class LogoutUseCase(
    private val userRepository: UserRepository
) {
    fun invoke() = userRepository.logOutUser()
}