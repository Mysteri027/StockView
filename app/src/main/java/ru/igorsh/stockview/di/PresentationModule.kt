package ru.igorsh.stockview.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.igorsh.stockview.presentation.screens.login.LoginViewModel
import ru.igorsh.stockview.presentation.screens.news.NewsViewModel
import ru.igorsh.stockview.presentation.screens.profile.ProfileViewModel
import ru.igorsh.stockview.presentation.screens.registration.RegistrationViewModel
import ru.igorsh.stockview.presentation.screens.search.SearchViewModel
import ru.igorsh.stockview.presentation.screens.splash.SplashViewModel

val presentationModule = module {

    viewModel {
        LoginViewModel(loginUseCase = get(), setAuthStatusUseCase = get())
    }

    viewModel {
        RegistrationViewModel(registerUseCase = get(), setAuthStatusUseCase = get())
    }

    viewModel {
        SplashViewModel(getAuthStatusUseCase = get())
    }

    viewModel {
        ProfileViewModel(logoutUseCase = get(), setAuthStatusUseCase = get())
    }

    viewModel {
        NewsViewModel()
    }

    viewModel {
        SearchViewModel()
    }
}