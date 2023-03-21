package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.local.UserStorage

class SetAuthStatusUseCase(
    private val userStorage: UserStorage
) {
    fun invoke(status: Boolean) = userStorage.set(status)
}