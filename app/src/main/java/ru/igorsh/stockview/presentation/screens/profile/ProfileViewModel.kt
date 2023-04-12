package ru.igorsh.stockview.presentation.screens.profile

import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor

class ProfileViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
) : ViewModel() {

    fun logout() {
        networkInteractor.logOutUser()
        localStorageInteractor.setAuthStatus(false)
    }
}