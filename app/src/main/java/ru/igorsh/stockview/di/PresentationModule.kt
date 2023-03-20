package ru.igorsh.stockview.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.igorsh.stockview.presentation.screens.login.LoginViewModel
import ru.igorsh.stockview.presentation.screens.registration.RegistrationViewModel

val presentationModule = module {

    viewModel {
        LoginViewModel(loginUseCase = get())
    }

    viewModel {
        RegistrationViewModel(registerUseCase = get())
    }
}