package ru.igorsh.stockview.presentation.screens.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.User

class RegistrationViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    private val _authStatus = MutableLiveData<Boolean>()
    val authStatus: LiveData<Boolean> = _authStatus

    suspend fun register(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val authResponse = networkInteractor.registerUser(user)

            if (authResponse.isSuccessful && authResponse.code() == 200) {
                val token = authResponse.body()?.token
                if (token != null) {
                    localStorageInteractor.saveToken(token)
                    _authStatus.postValue(true)
                    setAuthStatus(true)
                }
            }
        }
    }

    private fun setAuthStatus(status: Boolean) {
        localStorageInteractor.setAuthStatus(status)
    }

    fun isEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }
}