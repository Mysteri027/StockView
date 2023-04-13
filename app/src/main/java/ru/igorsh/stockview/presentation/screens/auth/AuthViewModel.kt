package ru.igorsh.stockview.presentation.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor

class AuthViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    private val _authStatus = MutableLiveData<Boolean>()
    val authStatus: LiveData<Boolean> = _authStatus

    private var _authScreenState = MutableLiveData(AuthScreenState.LOGIN_SCREEN)
    val authScreenState: LiveData<AuthScreenState> = _authScreenState

    suspend fun authUser(email: String, password: String, state: AuthScreenState) {
        val authScreenManager = AuthScreenManager(networkInteractor, localStorageInteractor)
        authScreenManager.authUser(state, email, password)
        _authStatus.postValue(localStorageInteractor.getAuthStatus())
    }

    fun setAuthScreenState(state: AuthScreenState) {
        _authScreenState.postValue(state)
    }

    fun isEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }
}