package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.local.UserStorage

class GetAuthStatusUseCase(
    private val userStorage: UserStorage
) {
    fun invoke() = userStorage.get()
}