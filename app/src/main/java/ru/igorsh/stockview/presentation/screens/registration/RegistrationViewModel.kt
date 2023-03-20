package ru.igorsh.stockview.presentation.screens.registration

import androidx.lifecycle.ViewModel
import ru.igorsh.stockview.domain.interactor.RegisterUseCase

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
}