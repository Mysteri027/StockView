package ru.igorsh.stockview.presentation.screens.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.User

class AuthScreenManager(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor
) {

    suspend fun authUser(state: AuthScreenState, email: String, password: String) {

        withContext(Dispatchers.IO) {
            val user = User(email, password)

            val token = when (state) {
                AuthScreenState.LOGIN_SCREEN -> networkInteractor.loginUser(user)
                AuthScreenState.REGISTER_SCREEN -> networkInteractor.registerUser(user)
            }
            if (token != "") {
                localStorageInteractor.saveToken(token)
                localStorageInteractor.setAuthStatus(true)
            }
        }
    }
}


enum class AuthScreenState {
    LOGIN_SCREEN,
    REGISTER_SCREEN
}