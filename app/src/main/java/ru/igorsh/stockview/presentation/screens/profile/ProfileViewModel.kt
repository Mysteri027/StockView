package ru.igorsh.stockview.presentation.screens.profile

import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.LogoutUseCase
import ru.igorsh.stockview.domain.interactor.SetAuthStatusUseCase

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val setAuthStatusUseCase: SetAuthStatusUseCase
): ViewModel() {

    fun logout() {
        logoutUseCase.invoke()
        setAuthStatusUseCase.invoke(false)
    }
}