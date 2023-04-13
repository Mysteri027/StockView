package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.local.UserStorage

class LocalStorageInteractor(
    private val userStorage: UserStorage
) {
    fun getAuthStatus() = userStorage.get()

    fun setAuthStatus(status: Boolean) = userStorage.set(status)

    fun saveToken(token: String) = userStorage.saveToken(token)
    fun getToken() = userStorage.getToken()
}