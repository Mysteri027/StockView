package ru.igorsh.stockview.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import ru.igorsh.stockview.domain.interactor.LoginUseCase
import ru.igorsh.stockview.domain.interactor.SetAuthStatusUseCase
import ru.igorsh.stockview.domain.model.User

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setAuthStatusUseCase: SetAuthStatusUseCase
) : ViewModel() {
    fun login(user: User): Task<AuthResult> {
        return loginUseCase.invoke(user)
    }

    fun setAuthStatus(status: Boolean) {
        setAuthStatusUseCase.invoke(status)
    }
}