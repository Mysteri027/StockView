package ru.igorsh.stockview.presentation.screens.registration

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import ru.igorsh.stockview.domain.interactor.RegisterUseCase
import ru.igorsh.stockview.domain.interactor.SetAuthStatusUseCase
import ru.igorsh.stockview.domain.model.User

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase,
    private val setAuthStatusUseCase: SetAuthStatusUseCase
) : ViewModel() {

    fun register(user: User): Task<AuthResult> {
        return registerUseCase.invoke(user)
    }

    fun setAuthStatus(status: Boolean) {
        setAuthStatusUseCase.invoke(status)
    }
}