package ru.igorsh.stockview.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.User

class LoginViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    private val _authStatus = MutableLiveData<Boolean>()
    val authStatus: LiveData<Boolean> = _authStatus

    suspend fun login(user: User) {
        withContext(Dispatchers.IO) {
            val authResponse = networkInteractor.loginUser(user)

            if (authResponse.isSuccessful && authResponse.code() == 200) {
                val token = authResponse.body()?.token
                if (token != null) {
                    _authStatus.postValue(true)
                    localStorageInteractor.saveToken(token)
                    setAuthStatus(true)
                }
            }
        }
    }

    private fun setAuthStatus(status: Boolean) {
        localStorageInteractor.setAuthStatus(status)
    }

    fun getAuthStatus(): Boolean {
        return localStorageInteractor.getAuthStatus()
    }

    fun isEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }
}