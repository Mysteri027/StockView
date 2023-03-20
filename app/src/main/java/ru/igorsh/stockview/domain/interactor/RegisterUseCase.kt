package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.domain.model.User
import ru.igorsh.stockview.domain.repository.UserRepository

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    fun invoke(user: User) = userRepository.registerUser(user)
}