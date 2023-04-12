package ru.igorsh.stockview.presentation.screens.registration

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.User

class RegistrationViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    fun register(user: User): Task<AuthResult> {
        return networkInteractor.registerUser(user)
    }

    fun setAuthStatus(status: Boolean) {
        localStorageInteractor.setAuthStatus(status)
    }

    fun isEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() or password.isEmpty()
    }
}