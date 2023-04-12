package ru.igorsh.stockview.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.User

class LoginViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {
    fun login(user: User): Task<AuthResult> {
        return networkInteractor.loginUser(user)
    }

    fun setAuthStatus(status: Boolean) {
        localStorageInteractor.setAuthStatus(status)
    }

    fun isEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }
}