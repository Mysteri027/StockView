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
        LoginViewModel(networkInteractor = get(), localStorageInteractor = get())
    }

    viewModel {
        RegistrationViewModel(networkInteractor = get(), localStorageInteractor = get())
    }

    viewModel {
        SplashViewModel(localStorageInteractor = get())
    }

    viewModel {
        ProfileViewModel(networkInteractor = get(), localStorageInteractor = get())
    }

    viewModel {
        NewsViewModel(
            insertLocalDataBaseMapper = get(),
            getLocalDataBaseMapper = get(),
            responseMapper = get(),
            localDataBaseInteractor = get(),
            networkInteractor = get()
        )
    }

    viewModel {
        SearchViewModel()
    }
}