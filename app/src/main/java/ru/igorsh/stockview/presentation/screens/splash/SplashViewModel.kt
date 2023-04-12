package ru.igorsh.stockview.presentation.screens.splash

import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor

class SplashViewModel(
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    fun getAuthStatus() = localStorageInteractor.getAuthStatus()
}