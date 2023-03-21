package ru.igorsh.stockview.presentation.screens.splash

import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.GetAuthStatusUseCase

class SplashViewModel(
    private val getAuthStatusUseCase: GetAuthStatusUseCase
) : ViewModel() {

    fun getAuthStatus() = getAuthStatusUseCase.invoke()
}